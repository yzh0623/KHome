package com.kida.home.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kida.home.bean.reptile.LinkTypeData;
import com.kida.home.bean.reptile.Rule;
import com.kida.home.service.ExtractService;
import com.kida.home.util.TextUtil;
import com.kida.home.util.properties.ReptileProperty;

@Service
public class ExtractServiceImpl implements ExtractService {

	private static final Logger logger = LoggerFactory
			.getLogger(ExtractServiceImpl.class);

	@Resource(name = "reptileProperty")
	private ReptileProperty reptileProperty;

	/**
	 * 发送规则信息进行数据爬虫
	 * 
	 * @param rule
	 * @return
	 */
	@Override
	public List<LinkTypeData> extract(Rule rule) {

		List<LinkTypeData> datas = new ArrayList<>();
		LinkTypeData data = null;
		try {
			// 验证输入规则
			validateRule(rule);

			String url = rule.getUrl();
			String[] params = rule.getParams();
			List<String> values = rule.getValues();
			String resultTagName = rule.getResultTagName();
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();

			Connection conn = Jsoup.connect(url);

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values.get(i));
				}
			}

			Document doc;
			if (requestType == Rule.GET) {
				doc = conn.timeout(500000).get();
			} else {
				doc = conn.timeout(500000).post();
			}

			Elements results = new Elements();
			switch (type) {
			case Rule.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case Rule.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case Rule.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				if (TextUtil.isEmpty(resultTagName)) {
					results = doc.getElementsByTag("body");
				}
			}

			for (Element result : results) {
				Elements links = result.getElementsByTag("a");

				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text();

					data = new LinkTypeData();
					data.setLinkHref(linkHref);
					data.setLinkText(linkText);

					datas.add(data);
				}
			}
		} catch (Exception e) {
			logger.info(Arrays.toString(e.getStackTrace()));
		}
		return datas;
	}

	/**
	 * 验证规则信息
	 * 
	 * @throws Exception
	 */
	private void validateRule(Rule rule) throws Exception {
		String url = rule.getUrl();
		if (TextUtil.isEmpty(url)) {
			throw new Exception(reptileProperty.getVldUrlIsEmpty());
		}
		if (!url.startsWith("http://")) {
			throw new Exception(reptileProperty.getVldHttp());
		}

		if (rule.getParams() != null && rule.getValues() != null) {
			if (rule.getParams().length != rule.getValues().size()) {
				throw new Exception(reptileProperty.getParam());
			}
		}
	}

	/**
	 * 调用爬虫接口
	 */
	@Override
	public Map<String, String> extWebArticle(String keyParam) {
		Map<String, String> reMap = new HashMap<String, String>();

		String url = reptileProperty.getUrl();
		String resultTag = reptileProperty.getResultTag();

		String[] params = reptileProperty.getParam().split(",");

		String[] keywords = reptileProperty.getKeyword().split(",");
		String query = "";
		for (String keys : keywords) {
			query += keys + "+";
		}
		if (!keyParam.isEmpty()) {
			query += keyParam;
		} else {
			query = query.substring(0, query.length() - 1);
		}
		List<String> keyList = new ArrayList<String>();
		keyList.add(query);
		keyList.add("blog");

		Rule rule = new Rule(url, params, keyList, resultTag, 0, Rule.GET);
		List<LinkTypeData> extracts = extract(rule);

		for (LinkTypeData data : extracts) {
			if (data.getLinkHref().indexOf("http://") > -1) {
				reMap.put(data.getLinkText(), data.getLinkHref());
			}
		}
		return reMap;
	}

}
