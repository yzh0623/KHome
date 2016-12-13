package com.kida.home.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kida.home.bean.vo.ArticleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class ArticleServiceTest extends AbstractJUnit4SpringContextTests {

	private static final Logger logger = LoggerFactory
			.getLogger(ArticleServiceTest.class);

	@Autowired
	private ArticleService articleService;

	@Test
	public void testQueryNextRowArticle() {
		List<ArticleVO> avList = articleService.queryNextRowArticle("6");
		if (null != avList && !avList.isEmpty()) {
			for (ArticleVO av : avList) {
				logger.debug(" ArticleId: " + av.getArticleId());
			}
		}
	}
}
