package com.kida.home.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("reptileProperty")
public class ReptileProperty {

	@Value("#{reptile[url]}")
	private String url;

	@Value("#{reptile[baseUrl]}")
	private String baseUrl;

	@Value("#{reptile[searchUrl]}")
	private String searchUrl;

	@Value("#{reptile[param]}")
	private String param;

	@Value("#{reptile[keyword]}")
	private String keyword;

	@Value("#{reptile[resultTag]}")
	private String resultTag;

	@Value("#{reptile[vldUrlIsEmpty]}")
	private String vldUrlIsEmpty;

	@Value("#{reptile[vldHttp]}")
	private String vldHttp;

	@Value("#{reptile[vldParam]}")
	private String vldParam;

	@Value("#{reptile[resultTagInfo]}")
	private String resultTagInfo;

	@Value("#{reptile[timeerParam]}")
	private String timeerParam;

	public String getTimeerParam() {
		return timeerParam;
	}

	public void setTimeerParam(String timeerParam) {
		this.timeerParam = timeerParam;
	}

	public String getResultTagInfo() {
		return resultTagInfo;
	}

	public void setResultTagInfo(String resultTagInfo) {
		this.resultTagInfo = resultTagInfo;
	}

	public String getVldUrlIsEmpty() {
		return vldUrlIsEmpty;
	}

	public void setVldUrlIsEmpty(String vldUrlIsEmpty) {
		this.vldUrlIsEmpty = vldUrlIsEmpty;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public String getVldHttp() {
		return vldHttp;
	}

	public void setVldHttp(String vldHttp) {
		this.vldHttp = vldHttp;
	}

	public String getVldParam() {
		return vldParam;
	}

	public void setVldParam(String vldParam) {
		this.vldParam = vldParam;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getResultTag() {
		return resultTag;
	}

	public void setResultTag(String resultTag) {
		this.resultTag = resultTag;
	}

}
