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

import com.kida.home.bean.ArticleLabel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring/applicationContext.xml" })
public class ArticleLabelServiceTest extends AbstractJUnit4SpringContextTests {

	private static final Logger logger = LoggerFactory
			.getLogger(ArticleLabelServiceTest.class);

	@Autowired
	private ArticleLabelService articleLabelService;

	@Test
	public void testQueryArticleLabelByArticleId() {
		List<ArticleLabel> alList = articleLabelService
				.queryArticleLabelByArticleId("KCwW8Q4E3na4tDzB");
		if (null != alList && !alList.isEmpty()) {
			for (ArticleLabel al : alList) {
				logger.info(" label: " + al.getLabel());
			}
		}
	}

	@Test
	public void testQueryArticleLabelByArticleIdAndLabel() {
		ArticleLabel al = articleLabelService
				.queryArticleLabelByArticleIdAndLabel("KCwW8Q4E3na4tDzB",
						"ORACLE");
		if (null != al) {
			logger.info(" aid: " + al.getArticleId());
		}
	}

	@Test
	public void testSaveArticleLabel() {
		ArticleLabel articleLabel = new ArticleLabel();
		articleLabel.setArticleId("KCwW8Q4E3na4tDzB");
		articleLabel.setLabel("Junit");
		int i = articleLabelService.saveArticleLabel(articleLabel);
		logger.info(" complete counter: " + i);
	}

}
