package com.kida.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kida.home.bean.BriefPic;
import com.kida.home.bean.vo.BriefPicVO;
import com.kida.home.dao.BriefPicDAO;
import com.kida.home.service.BriefPicService;
import com.kida.home.util.properties.ConfigProperty;

@Service
public class BriefPicServiceImpl implements BriefPicService {

	@Autowired
	private BriefPicDAO briefPicDAO;

	@Resource(name = "configProperty")
	private ConfigProperty configProperty;

	/**
	 * 采用先删除后插入的方式进行图片的信息更新
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertBriefPic(BriefPic briefPic) {
		briefPicDAO.insertBriefPic(briefPic);
	}

	@Override
	public void deleteBriefPicByArticleId(String articleId) {
		briefPicDAO.deleteBriefPicByArticleId(articleId);
	}

	@Override
	public List<BriefPicVO> queryBriefPicVOByArticleId(String articleId) {
		BriefPicVO briefPicVO = null;
		List<BriefPicVO> briefPicVOList = new ArrayList<BriefPicVO>();

		String imagePath = configProperty.getImagePath();
		String imageUrl = configProperty.getImageUrl();
		String url = null;

		List<BriefPic> briefPicList = briefPicDAO
				.queryBriefPicByArticleId(articleId);
		if (null != briefPicList && !briefPicList.isEmpty()) {
			for (BriefPic briefPic : briefPicList) {
				briefPicVO = new BriefPicVO();
				BeanUtils.copyProperties(briefPic, briefPicVO);
				url = briefPic.getPicPath().replace(imagePath, imageUrl);
				briefPicVO.setUrl(url);
				briefPicVOList.add(briefPicVO);
			}
		}
		return briefPicVOList;
	}

	@Override
	public List<BriefPic> queryBriefPicByCutStatus(int cutStatus) {
		return briefPicDAO.queryBriefPicByCutStatus(cutStatus);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateBriefPic(BriefPic briefPic) {
		briefPicDAO.updateBriefPic(briefPic);
	}

}
