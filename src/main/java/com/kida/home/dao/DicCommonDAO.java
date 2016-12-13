package com.kida.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kida.home.bean.DicCommon;

public interface DicCommonDAO {

	/**
	 * 通过分类id查询字典表
	 * 
	 * @param groupId
	 * @return
	 */
	List<DicCommon> queryDicCommonByGroupId(
			@Param(value = "groupId") String groupId);

	/**
	 * 通过分类id和编码字段查询字典表
	 * 
	 * @param groupId
	 * @param code
	 * @return
	 */
	DicCommon queryDicCommonByGroupIdAndCode(
			@Param(value = "groupId") String groupId,
			@Param(value = "code") String code);

	/**
	 * 保存字典信息
	 * 
	 * @param dicCommon
	 * @return
	 */
	int saveDicCommon(@Param(value = "dicCommon") DicCommon dicCommon);
}
