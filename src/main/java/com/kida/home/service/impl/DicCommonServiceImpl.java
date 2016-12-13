package com.kida.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kida.home.bean.DicCommon;
import com.kida.home.dao.DicCommonDAO;
import com.kida.home.service.DicCommonService;

@Service
public class DicCommonServiceImpl implements DicCommonService {

	@Autowired
	private DicCommonDAO dicCommonDAO;

	@Override
	public List<DicCommon> queryDicCommonByGroupId(String groupId) {
		return dicCommonDAO.queryDicCommonByGroupId(groupId);
	}

	@Override
	public DicCommon queryDicCommonByGroupIdAndCode(String groupId, String code) {
		return dicCommonDAO.queryDicCommonByGroupIdAndCode(groupId, code);
	}

	@Override
	public int saveDicCommon(DicCommon dicCommon) {
		return dicCommonDAO.saveDicCommon(dicCommon);
	}

}
