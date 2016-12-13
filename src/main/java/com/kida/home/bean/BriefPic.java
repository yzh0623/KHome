package com.kida.home.bean;

public class BriefPic {

	private String picId;// 图片id
	private String articleId;// 文章id
	private String picPath;// 图片路径
	private Integer sortId;// 序号
	private Integer cutStatus;// 裁剪状态

	public Integer getCutStatus() {
		return cutStatus;
	}

	public void setCutStatus(Integer cutStatus) {
		this.cutStatus = cutStatus;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

}
