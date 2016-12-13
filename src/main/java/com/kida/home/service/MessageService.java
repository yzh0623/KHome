package com.kida.home.service;

import com.kida.home.bean.Message;

public interface MessageService {

	/**
	 * 保存文章转化信息
	 * 
	 * @param message
	 */
	public void saveMessage(Message message);

}
