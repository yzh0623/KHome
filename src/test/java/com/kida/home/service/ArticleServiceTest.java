package com.kida.home.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kida.home.bean.Article;
import com.kida.home.bean.vo.ArticleVO;
import com.kida.home.util.UUIDGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring/applicationContext.xml" })
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
				logger.info(" ArticleId: " + av.getArticleId());
			}
		}
	}

	@Test
	public void testQueryArticle4Show() {
		List<ArticleVO> avList = articleService.queryArticle4Show("4");
		if (null != avList && !avList.isEmpty()) {
			for (ArticleVO av : avList) {
				logger.info(" ArticleId: " + av.getArticleId());
			}
		}
	}

	@Test
	public void testQueryLogsArticleLine() {
		List<ArticleVO> avList = articleService.queryLogsArticleLine();
		if (null != avList && !avList.isEmpty()) {
			for (ArticleVO av : avList) {
				logger.info(" ArticleId: " + av.getArticleId());
			}
		}
	}

	@Test
	public void testQueryExperArticleLine() {
		List<ArticleVO> avList = articleService.queryExperArticleLine();
		if (null != avList && !avList.isEmpty()) {
			for (ArticleVO av : avList) {
				logger.info(" ArticleId: " + av.getArticleId());
			}
		}
	}

	@Test
	public void testQueryArticleById() {
		Article article = articleService.queryArticleById("humSoKEwE7Wknftk");
		if (null != article) {
			logger.info(" ArticleId: " + article.getArticleId());
		}
	}

	@Test
	public void testQueryArticleByCate() {
		List<ArticleVO> avList = articleService.queryArticleByCate("experiment",
				"2");
		if (null != avList && !avList.isEmpty()) {
			for (ArticleVO av : avList) {
				logger.info(" ArticleId: " + av.getArticleId());
			}
		}
	}

	@Test
	public void testSaveArticle() {
		Article article = new Article();
		article.setArticleId(UUIDGenerator.uuidGenerator(16));
		article.setBrief("testing2");
		article.setCategory("LOGS");
		article.setCreateTime(new Date());
		article.setReadCount(0);
		article.setSummary("testing 2");
		article.setTitle("testing in junit");
		int i = articleService.saveArticle(article);
		logger.info(" complete counter: " + i);

	}

	@Test
	public void testQueryArticleByTitle() {
		Article article = articleService
				.queryArticleByTitle("testing in junit");
		if (null != article) {
			logger.info(" ArticleId: " + article.getArticleId());
		}
	}

	@Test
	public void testQueryArticleByLabel() {
		List<ArticleVO> avList = articleService.queryArticleByLabel("Mysql",
				"2");
		if (null != avList && !avList.isEmpty()) {
			for (ArticleVO av : avList) {
				logger.info(" ArticleId: " + av.getArticleId());
			}
		}
	}
}
