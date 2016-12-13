package com.kida.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kida.home.bean.ArticleLabel;

public interface ArticleLabelDAO {

	/**
	 * 通过articleId查询ArticleLabel
	 * 
	 * @param articleId
	 * @return
	 */
	List<ArticleLabel> queryArticleLabelByArticleId(
			@Param("articleId") String articleId);

	/**
	 * 通过文章id和标签找到具体信息
	 * 
	 * @param articleId
	 * @param label
	 * @return
	 */
	ArticleLabel queryArticleLabelByArticleIdAndLabel(
			@Param("articleId") String articleId, @Param("label") String label);

	/**
	 * 保存ArticleLabel
	 * 
	 * @param articleLabel
	 * @return
	 */
	int saveArticleLabel(@Param("articleLabel") ArticleLabel articleLabel);
}
