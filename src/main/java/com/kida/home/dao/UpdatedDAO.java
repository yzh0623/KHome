package com.kida.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kida.home.bean.Updated;

public interface UpdatedDAO {

	/**
	 * 查询最新记录
	 * 
	 * @return
	 */
	List<Updated> queryLastestDayUpdate();

	/**
	 * 查询上一次记录
	 * 
	 * @param oldDay
	 * @return
	 */
	List<Updated> queryNextDay(@Param(value = "oldDay") String oldDay);

	/**
	 * 保存Updated信息
	 * 
	 * @param updated
	 * @return
	 */
	int insertUpdated(@Param(value = "updated") Updated updated);
}
