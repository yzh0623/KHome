package com.kida.home.service;

import java.util.List;

import com.kida.home.bean.Updated;

/**
 * 查询更新信息
 * 
 * @author kida
 *
 */
public interface UpdatedService {
	/**
	 * 查询最近更新内容
	 * 
	 * @return
	 */
	List<Updated> queryLastestDayUpdate();

	/**
	 * 根据传入日期找到这次日期之前的一次更新记录
	 * 
	 * @param oldDay
	 * @return
	 */
	List<Updated> queryNextDay(String oldDay);

	/**
	 * 保存更新信息
	 * 
	 * @param updateInfo
	 * @return
	 */
	String saveUpdateInfo(String updateInfo);
}
