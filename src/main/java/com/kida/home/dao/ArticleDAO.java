package com.kida.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kida.home.bean.Article;
import com.kida.home.bean.vo.ArticleVO;

/**
 * DAO接口
 * 
 * @author kida
 *
 */
public interface ArticleDAO {

	/**
	 * 查询下一行文章
	 * 
	 * @param begin
	 *            开始区间
	 * @param end
	 *            结束区间
	 * @return
	 */
	List<ArticleVO> queryNextRowArticle(@Param("begin") int begin,
			@Param("end") int end);

	/**
	 * 显示首页文档简要
	 * 
	 * @param showBlock
	 *            显示块数
	 * @return
	 */
	List<ArticleVO> queryArticle4Show(@Param("showBlock") int showBlock);

	/**
	 * 首页查询logs文章条目
	 * 
	 * @return
	 */
	List<ArticleVO> queryCateArticleLine(@Param("cateType") String cateType);

	/**
	 * 根据id查询到文章详细信息
	 * 
	 * @param id
	 *            文章id
	 * @return
	 */
	Article queryArticleById(@Param("id") String id);

	/**
	 * 根据类型查询文章
	 * 
	 * @param cateType
	 *            类型
	 * @param begin
	 *            开始条数
	 * @param end
	 *            结束条数
	 * @return
	 */
	List<ArticleVO> queryArticleByCate(@Param("cateType") String cateType,
			@Param("begin") int begin, @Param("end") int end);

	/**
	 * 模糊查询文章列表
	 * 
	 * @param keyWord
	 *            关键字
	 * @return
	 */
	List<ArticleVO> queryArticleByCate4Search(@Param("keyWord") String keyWord);

	/**
	 * 更新当前查阅次数
	 * 
	 * @param articleId
	 *            文章id
	 */
	void updateArticleReadCount(@Param("articleId") String articleId);

	/**
	 * 保存文章信息
	 * 
	 * @param article
	 * @return
	 */
	int saveArticle(@Param("article") Article article);

	/**
	 * 根据标题查询文章
	 * 
	 * @param title
	 * @return
	 */
	Article queryArticleByTitle(@Param("title") String title);

	/**
	 * 根据标签查询文章列表
	 * 
	 * @param keyword
	 * @param begin
	 * @param end
	 * @return
	 */
	List<ArticleVO> queryArticleByLabel(@Param("keyword") String keyword,
			@Param("begin") int begin, @Param("end") int end);

}
