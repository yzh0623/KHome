package com.kida.home.service;

import java.util.List;

import com.kida.home.bean.ArticleLabel;

public interface ArticleLabelService {

	/**
	 * 通过文章id查询标签
	 * 
	 * @param articleId
	 * @return
	 */
	public List<ArticleLabel> queryArticleLabelByArticleId(String articleId);

	/**
	 * 通过文章id和标签找到具体信息
	 * 
	 * @param articleId
	 * @param label
	 * @return
	 */
	public ArticleLabel queryArticleLabelByArticleIdAndLabel(String articleId,
			String label);

	/**
	 * 保存ArticleLabel
	 * 
	 * @param articleLabel
	 * @return
	 */
	public int saveArticleLabel(ArticleLabel articleLabel);

}
