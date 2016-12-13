package com.kida.home.service;

import java.util.List;

import com.kida.home.bean.BriefPic;
import com.kida.home.bean.vo.BriefPicVO;

/**
 * 文章内容图片保存Service
 * 
 * @author kida
 *
 */
public interface BriefPicService {

	/**
	 * 插入主信息图片
	 * 
	 * @param briefPic
	 */
	public void insertBriefPic(BriefPic briefPic);

	/**
	 * 更新主信息图片
	 * 
	 * @param briefPic
	 */
	public void updateBriefPic(BriefPic briefPic);

	/**
	 * 根据articleId删除图片
	 * 
	 * @param articleId
	 */
	public void deleteBriefPicByArticleId(String articleId);

	/**
	 * 通过articleId找到主信息图片
	 * 
	 * @param articleId
	 * @return
	 */
	public List<BriefPicVO> queryBriefPicVOByArticleId(String articleId);

	/**
	 * 通过裁剪状态找到图片
	 * 
	 * @param cutStatus
	 * @return
	 */
	public List<BriefPic> queryBriefPicByCutStatus(int cutStatus);
}
