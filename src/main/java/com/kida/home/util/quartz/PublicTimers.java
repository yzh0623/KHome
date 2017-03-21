package com.kida.home.util.quartz;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kida.home.service.ContentConverService;
import com.kida.home.service.ExtractService;
import com.kida.home.util.properties.ReptileProperty;

@Component
public class PublicTimers {

	@Autowired
	private ContentConverService contentConverService;

	@Autowired
	private ExtractService extractService;

	@Resource(name = "reptileProperty")
	private ReptileProperty reptileProperty;

	/**
	 * 每天中午12点和晚上23点发布一次文章
	 */

	// @Scheduled(cron = "0 50 11,23 * * ?")
	@Scheduled(cron = "0 37 17 * * ?")
	public void doPublish() {
		contentConverService.doIt();
	}

	/**
	 * 每天凌晨3点获取爬虫数据
	 */
	@Scheduled(cron = "0 00 3 * * ?")
	public void doExtract() {
		extractService.init2Extract();
	}
}
