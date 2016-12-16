package com.kida.home.dao;

import org.apache.ibatis.annotations.Param;

import com.kida.home.bean.Message;

@FunctionalInterface
public interface MessageDAO {

	/**
	 * 插入到信息
	 * 
	 * @param message
	 * @return
	 */
	int insertMessage(@Param(value = "message") Message message);
}
