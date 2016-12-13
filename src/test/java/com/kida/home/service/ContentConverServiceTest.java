package com.kida.home.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class ContentConverServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private ContentConverService contentConverService;

	@Test
	public void testDoIt() {
		contentConverService.doIt();
	}
}
