package com.kida.home.util.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.kida.home.service.ExtractService;

@Component
public class ContainerInitProcess
		implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ExtractService extractService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		extractService.init2Extract();
	}

}
