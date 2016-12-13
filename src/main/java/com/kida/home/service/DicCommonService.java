package com.kida.home.service;

import java.util.List;

import com.kida.home.bean.DicCommon;

public interface DicCommonService {

	/**
	 * 通过分类id查询字典表
	 * 
	 * @param groupId
	 * @return
	 */
	public List<DicCommon> queryDicCommonByGroupId(String groupId);

	/**
	 * 通过分类id和编码字段查询字典表
	 * 
	 * @param groupId
	 * @param code
	 * @return
	 */
	public DicCommon queryDicCommonByGroupIdAndCode(String groupId, String code);

	/**
	 * 保存字典信息
	 * 
	 * @param dicCommon
	 * @return
	 */
	public int saveDicCommon(DicCommon dicCommon);

}
