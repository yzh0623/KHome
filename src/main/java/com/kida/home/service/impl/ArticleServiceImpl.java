package com.kida.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kida.home.bean.Article;
import com.kida.home.bean.vo.ArticleVO;
import com.kida.home.dao.ArticleDAO;
import com.kida.home.service.ArticleService;
import com.kida.home.util.properties.ConfigProperty;

/**
 * 业务逻辑处理类
 * 
 * @author kida
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDAO articleDAO;

	@Resource(name = "configProperty")
	private ConfigProperty configProperty;

	@Override
	public List<ArticleVO> queryNextRowArticle(String lastLocation) {
		// 当前位置
		int nowLocate = Integer.parseInt(lastLocation);
		// 下一行开始位置
		int begin = nowLocate + 1;
		// 下一行结束位置
		int end = nowLocate + 3;
		// 查询区间
		return articleDAO.queryNextRowArticle(begin, end);
	}

	@Override
	public List<ArticleVO> queryArticle4Show(String showNum) {
		// 初始化显示区域块个数
		int showBlock = Integer.parseInt(showNum);
		return articleDAO.queryArticle4Show(showBlock);
	}

	@Override
	public List<ArticleVO> queryLogsArticleLine() {
		return articleDAO.queryCateArticleLine("LOGS");
	}

	@Override
	public List<ArticleVO> queryExperArticleLine() {
		return articleDAO.queryCateArticleLine("EXPERIMENT");
	}

	@Override
	public Article queryArticleById(String id) {
		return articleDAO.queryArticleById(id);
	}

	@Override
	public List<ArticleVO> queryArticleByCate(String cateType,
			String nowLocation) {
		String cateShow = configProperty.getCateShow();
		// 分类列表中开始条数
		int begin = Integer.parseInt(nowLocation);
		// 结束条数
		int end = begin + Integer.parseInt(cateShow);
		// 查询区间
		return articleDAO.queryArticleByCate(cateType, begin, end);
	}

	@Override
	public List<ArticleVO> queryArticleByCate(String keyWord) {
		return articleDAO.queryArticleByCate4Search(keyWord);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateArticleReadCount(String articleId) {
		articleDAO.updateArticleReadCount(articleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveArticle(Article article) {
		return articleDAO.saveArticle(article);
	}

	@Override
	public Article queryArticleByTitle(String title) {
		return articleDAO.queryArticleByTitle(title);
	}

	@Override
	public List<ArticleVO> queryArticleByLabel(String keyword,
			String nowLocation) {
		String cateShow = configProperty.getCateShow();
		// 分类列表中开始条数
		int begin = Integer.parseInt(nowLocation);
		// 结束条数
		int end = begin + Integer.parseInt(cateShow);
		// 查询区间
		return articleDAO.queryArticleByLabel(keyword, begin, end);
	}

}
