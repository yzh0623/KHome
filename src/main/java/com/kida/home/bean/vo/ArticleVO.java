package com.kida.home.bean.vo;

import com.kida.home.bean.Article;

/**
 * 文章VO类继承自文章类
 * 
 * @author kida
 *
 */
public class ArticleVO extends Article {

	private int days; // 天数

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

}
