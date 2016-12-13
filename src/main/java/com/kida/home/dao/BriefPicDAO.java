package com.kida.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kida.home.bean.BriefPic;

public interface BriefPicDAO {
	/**
	 * 根据文章id找到对应的图片
	 * 
	 * @param ArticleId
	 * @return
	 */
	List<BriefPic> queryBriefPicByArticleId(
			@Param(value = "articleId") String articleId);

	/**
	 * 根据文章id删除图片
	 * 
	 * @param articleId
	 * @return
	 */
	int deleteBriefPicByArticleId(@Param(value = "articleId") String articleId);

	/**
	 * 插入图片信息
	 * 
	 * @param briefPic
	 * @return
	 */
	int insertBriefPic(@Param(value = "briefPic") BriefPic briefPic);

	/**
	 * 更新图片信息
	 * 
	 * @param briefPic
	 * @return
	 */
	int updateBriefPic(@Param(value = "briefPic") BriefPic briefPic);

	/**
	 * 根据裁剪状态查询图片
	 * 
	 * @param cutStatus
	 * @return
	 */
	List<BriefPic> queryBriefPicByCutStatus(
			@Param(value = "cutStatus") int cutStatus);

}
