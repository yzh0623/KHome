package com.kida.home.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("configProperty")
public class ConfigProperty {

	@Value("#{config[blockShow]}")
	private String blockShow;

	@Value("#{config[cateShow]}")
	private String cateShow;

	@Value("#{config[pwd]}")
	private String pwd;

	@Value("#{config[nowDocPath]}")
	private String nowDocPath;

	@Value("#{config[archiveDocPath]}")
	private String archiveDocPath;

	@Value("#{config[imagePath]}")
	private String imagePath;

	@Value("#{config[imageUrl]}")
	private String imageUrl;

	@Value("#{config[extractFilePath]}")
	private String extractFilePath;

	public String getExtractFilePath() {
		return extractFilePath;
	}

	public void setExtractFilePath(String extractFilePath) {
		this.extractFilePath = extractFilePath;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getNowDocPath() {
		return nowDocPath;
	}

	public void setNowDocPath(String nowDocPath) {
		this.nowDocPath = nowDocPath;
	}

	public String getArchiveDocPath() {
		return archiveDocPath;
	}

	public void setArchiveDocPath(String archiveDocPath) {
		this.archiveDocPath = archiveDocPath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBlockShow() {
		return blockShow;
	}

	public void setBlockShow(String blockShow) {
		this.blockShow = blockShow;
	}

	public String getCateShow() {
		return cateShow;
	}

	public void setCateShow(String cateShow) {
		this.cateShow = cateShow;
	}

}
