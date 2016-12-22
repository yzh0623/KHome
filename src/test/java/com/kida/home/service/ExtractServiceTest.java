package com.kida.home.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kida.home.bean.reptile.LinkTypeData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring/applicationContext.xml" })
public class ExtractServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private ExtractService extractService;

	@Test
	public void testExtract() {
		// getDatasByCssQueryUserCSDN();
	}

	/*
	 * public void getDatasByCssQueryUserCSDN() { List<String> strLit = new
	 * ArrayList<String>(); strLit.add("Java"); strLit.add("blog");
	 * 
	 * Rule rule = new Rule("http://so.csdn.net/so/search/s.do", new String[] {
	 * "q", "t" }, strLit, "csdn-tracking-statistics", 0, Rule.GET);
	 * List<LinkTypeData> extracts = extractService.extract(rule);
	 * printf(extracts); }
	 */

	public void printf(List<LinkTypeData> datas) {
		List<LinkTypeData> ltdList = new ArrayList<>();
		for (LinkTypeData data : datas) {

			if (data.getLinkHref().indexOf("http://") > -1) {
				System.out.println(data.getLinkText());
				System.out.println(data.getLinkHref());
				System.out.println("***********************************");
				ltdList.add(data);
			}
		}

		for (LinkTypeData data : ltdList) {
			System.out.println(data.getLinkText());
			System.out.println(data.getLinkHref());
			System.out.println("***********************************");
		}
	}
}
