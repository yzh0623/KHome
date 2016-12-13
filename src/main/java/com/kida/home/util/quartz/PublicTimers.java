package com.kida.home.util.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kida.home.service.ContentConverService;

@Component
public class PublicTimers {

	@Autowired
	private ContentConverService contentConverService;

	/**
	 * 每天中午12点和晚上23点发布一次文章
	 */

	// @Scheduled(cron = "0 50 11,23 * * ?")
	@Scheduled(cron = "0 45 00 * * ?")
	public void doPublish() {
		contentConverService.doIt();
	}
}
