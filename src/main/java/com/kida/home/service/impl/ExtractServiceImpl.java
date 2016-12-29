package com.kida.home.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.kida.home.bean.reptile.LinkTypeData;
import com.kida.home.bean.reptile.Rule;
import com.kida.home.service.ExtractService;
import com.kida.home.util.properties.ReptileProperty;

@Service
public class ExtractServiceImpl implements ExtractService {

	private static final Logger LOG = Logger
			.getLogger(ExtractServiceImpl.class);

	@Resource(name = "reptileProperty")
	private ReptileProperty reptileProperty;

	private String[] params = null;
	private String[] keywords = null;
	private List<String> keyList;

	private void initParam() {
		params = reptileProperty.getParam().split(",");
		keywords = reptileProperty.getKeyword().split(",");
	}

	/**
	 * 爬取网页链接
	 */
	@Override
	public Map<String, String> extWebArticle(String keyParam) {
		initParam();
		Map<String, String> reMap = new HashMap<>();

		String url = reptileProperty.getBaseUrl()
				+ reptileProperty.getSearchUrl();
		String resultTag = reptileProperty.getResultTag();

		keyList = arrangeParam(keywords, keyParam);
		try {
			Rule rule = new Rule(url, params, keyList, resultTag, 0, Rule.GET);
			List<LinkTypeData> extracts = extract(rule);

			for (LinkTypeData data : extracts) {
				if (data.getLinkHref().indexOf("viewspace") > -1) {
					reMap.put(data.getLinkText(), data.getLinkHref());
				}
			}
		} catch (Exception e) {
			LOG.info(e.getStackTrace());
		}
		return reMap;
	}

	/**
	 * 爬取文章内容
	 */
	@Override
	public Map<String, String> extWebArticleInfo(String keyParam) {
		initParam();

		Map<String, String> reMap = new HashMap<>();

		int counter = 0;

		String content;
		String url = reptileProperty.getBaseUrl();
		String resultTagInfo = reptileProperty.getResultTagInfo();

		Rule rule;

		keyList = arrangeParam(keywords, keyParam);
		try {
			Map<String, String> map = extWebArticle(keyParam);
			if (!map.isEmpty()) {
				for (Map.Entry<String, String> entry : map.entrySet()) {

					if (counter == 10) {
						break;
					}

					rule = new Rule(url + entry.getValue(), params, keyList,
							resultTagInfo, 0, Rule.GET);

					content = extractContent(rule);
					if (!"".equals(content)) {
						reMap.put(entry.getKey(), content);
						counter++;
					}
				}
			}
		} catch (Exception e) {
			LOG.info(e.getStackTrace());
		}
		return reMap;
	}

	/**
	 * 访问网址并获取信息
	 * 
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	private List<LinkTypeData> extract(Rule rule) throws Exception {
		Document doc;
		List<LinkTypeData> datas;

		// 验证输入规则
		validateRule(rule);

		Connection conn = Jsoup.connect(rule.getUrl());
		settingParams(conn, rule);
		doc = connUrl(conn, rule);
		Elements results = chioseNode(doc, rule);
		datas = arrangeLink(results);
		return datas;
	}

	/**
	 * 爬取内容执行方式
	 * 
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	private String extractContent(Rule rule) throws Exception {
		Document doc;
		StringBuilder text;
		// 验证输入规则
		validateRule(rule);

		Connection conn = Jsoup.connect(rule.getUrl());
		settingParams(conn, rule);
		doc = connUrl(conn, rule);
		Elements results = chioseNode(doc, rule);
		text = arrangeContent(results);

		return text.toString();
	}

	/**
	 * 验证规则信息
	 * 
	 * @throws Exception
	 */
	private void validateRule(Rule rule) throws Exception {
		String url = rule.getUrl();
		if (url.isEmpty()) {
			throw new Exception(reptileProperty.getVldUrlIsEmpty());
		}
		if (!url.startsWith("http://")) {
			throw new Exception(reptileProperty.getVldHttp());
		}
		if (rule.getParams() != null && rule.getValues() != null
				&& rule.getParams().length != rule.getValues().size()) {
			throw new Exception(reptileProperty.getParam());
		}
	}

	/**
	 * 设置参数
	 * 
	 * @param conn
	 * @param rule
	 */
	private void settingParams(Connection conn, Rule rule) {
		String[] setPars = rule.getParams();
		List<String> values = rule.getValues();
		if (null != setPars) {
			for (int i = 0; i < setPars.length; i++) {
				conn.data(setPars[i], values.get(i));
			}
		}
	}

	/**
	 * 链接url
	 * 
	 * @param conn
	 * @param rule
	 * @return
	 * @throws IOException
	 */
	private Document connUrl(Connection conn, Rule rule) throws IOException {
		Document doc;
		int requestType = rule.getRequestMoethod();
		if (requestType == Rule.GET) {
			doc = conn.timeout(500000).get();
		} else {
			doc = conn.timeout(500000).post();
		}
		return doc;
	}

	/**
	 * 获取节点
	 * 
	 * @param doc
	 * @param rule
	 * @return
	 */
	private Elements chioseNode(Document doc, Rule rule) {
		int type = rule.getType();

		String resultTagName = rule.getResultTagName();

		Elements results = new Elements();
		switch (type) {
		case Rule.CLASS:
			results = doc.getElementsByClass(resultTagName);
			break;
		case Rule.ID:
			results.add(doc.getElementById(resultTagName));
			break;
		case Rule.SELECTION:
			results = doc.select(resultTagName);
			break;
		default:
			if (resultTagName.isEmpty()) {
				results = doc.getElementsByTag("body");
			}
		}
		return results;
	}

	/**
	 * 内容整理
	 * 
	 * @param results
	 * @return
	 */
	private StringBuilder arrangeContent(Elements results) {
		StringBuilder text = new StringBuilder("");
		for (Element result : results) {
			Elements links = result.getAllElements();
			for (int i = 1; i < links.size(); i++) {
				Element link = links.get(i);
				if (!"".equals(link.ownText())) {
					text.append(link.ownText()).append("<br/>");
				}
			}
		}
		return text;
	}

	/**
	 * 链接整理
	 * 
	 * @param results
	 * @return
	 */
	private List<LinkTypeData> arrangeLink(Elements results) {
		List<LinkTypeData> datas = new ArrayList<>();
		for (Element result : results) {
			Elements links = result.getElementsByTag("a");

			for (Element link : links) {
				String linkHref = link.attr("href");
				String linkText = link.text();

				LinkTypeData data = new LinkTypeData();
				data.setLinkHref(linkHref);
				data.setLinkText(linkText);

				datas.add(data);
			}
		}
		return datas;
	}

	/**
	 * 参数整理
	 * 
	 * @param keywords
	 * @param keyParam
	 * @return
	 */
	private List<String> arrangeParam(String[] keywords, String keyParam) {
		StringBuilder query = new StringBuilder("");
		List<String> paramList = new ArrayList<>();
		for (String keys : keywords) {
			query.append(keys).append("+");
		}
		if (null != keyParam) {
			query.append(keyParam);
		} else {
			query = new StringBuilder(query.substring(0, query.length() - 1));
		}

		paramList.add(query.toString());
		return paramList;
	}
}
