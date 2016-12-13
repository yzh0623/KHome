package com.kida.home.dao;

import org.apache.ibatis.annotations.Param;

import com.kida.home.bean.Message;

public interface MessageDAO {

	int insertMessage(@Param(value = "message") Message message);
}
