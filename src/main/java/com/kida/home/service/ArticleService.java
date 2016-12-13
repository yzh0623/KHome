package com.kida.home.service;

import java.util.List;

import com.kida.home.bean.Article;
import com.kida.home.bean.vo.ArticleVO;

/**
 * 业务逻辑接口
 * 
 * @author kida
 *
 */
public interface ArticleService {

	/**
	 * 查询下一行的文章
	 * 
	 * @param lastLocation
	 *            最后定位位置
	 * @return
	 */
	public List<ArticleVO> queryNextRowArticle(String lastLocation);

	/**
	 * 查询block块中的文章
	 * 
	 * @return
	 */
	public List<ArticleVO> queryArticle4Show(String showNum);

	/**
	 * 首页查询logs文章条目
	 * 
	 * @return
	 */
	public List<ArticleVO> queryLogsArticleLine();

	/**
	 * 首页查询exper文章条目
	 * 
	 * @return
	 */
	public List<ArticleVO> queryExperArticleLine();

	/**
	 * 根据id查询到对应文章详细信息
	 * 
	 * @param id
	 *            文章id
	 * @return
	 */
	public Article queryArticleById(String id);

	/**
	 * 查询类型列表
	 * 
	 * @param cateType
	 *            查询类型
	 * @param nowLocation
	 *            当前行数
	 * @return
	 */
	public List<ArticleVO> queryArticleByCate(String cateType,
			String nowLocation);

	/**
	 * 更新当前查阅次数
	 * 
	 * @param articleId
	 *            文章id
	 */
	public void updateArticleReadCount(String articleId);

	/**
	 * 保存文章信息
	 * 
	 * @param article
	 * @return
	 */
	public int saveArticle(Article article);

	/**
	 * 根据标题查询文章
	 * 
	 * @param title
	 * @return
	 */
	public Article queryArticleByTitle(String title);

	/**
	 * 根据标签搜索文章
	 * 
	 * @param keyword
	 * @param nowLocation
	 * @return
	 */
	public List<ArticleVO> queryArticleByLabel(String keyword,
			String nowLocation);
}
