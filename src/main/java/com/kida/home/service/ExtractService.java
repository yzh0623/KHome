package com.kida.home.service;

import java.util.List;
import java.util.Map;

import com.kida.home.bean.reptile.LinkTypeData;
import com.kida.home.bean.reptile.Rule;

public interface ExtractService {

	/**
	 * 发送规则信息进行数据爬虫
	 * 
	 * @param rule
	 * @return
	 */
	public List<LinkTypeData> extract(Rule rule);

	/**
	 * 调用爬取文章信息
	 * 
	 * @return
	 */
	public Map<String, String> extWebArticle(String keyParam);

}
