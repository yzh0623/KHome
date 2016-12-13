package com.kida.home.bean;

/**
 * 文章标签类
 * 
 * @author kida
 *
 */
public class ArticleLabel {

	private String articleId;// 关联文章id
	private String label;// 标签

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
