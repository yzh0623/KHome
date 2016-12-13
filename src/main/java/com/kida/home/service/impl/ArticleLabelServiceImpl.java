package com.kida.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kida.home.bean.ArticleLabel;
import com.kida.home.dao.ArticleLabelDAO;
import com.kida.home.service.ArticleLabelService;

@Service
public class ArticleLabelServiceImpl implements ArticleLabelService {

	@Autowired
	private ArticleLabelDAO articleLabelDAO;

	@Override
	public List<ArticleLabel> queryArticleLabelByArticleId(String articleId) {
		return articleLabelDAO.queryArticleLabelByArticleId(articleId);
	}

	@Override
	public int saveArticleLabel(ArticleLabel articleLabel) {
		return articleLabelDAO.saveArticleLabel(articleLabel);
	}

	@Override
	public ArticleLabel queryArticleLabelByArticleIdAndLabel(String articleId,
			String label) {
		return articleLabelDAO.queryArticleLabelByArticleIdAndLabel(articleId,
				label);
	}

}
