package com.kida.home.service;

import java.util.Map;

public interface ExtractService {

	/**
	 * 初始化时执行
	 */
	public void init2Extract();

	/**
	 * 调用爬取文章信息
	 * 
	 * @return
	 */
	public Map<String, String> extWebArticle(String keyParam);

	/**
	 * 爬虫文章内容
	 * 
	 * @param keyParam
	 * @return
	 */
	public Map<String, String> extWebArticleInfo(String keyParam);

}
