package com.kida.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kida.home.bean.Message;
import com.kida.home.dao.MessageDAO;
import com.kida.home.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Override
	public void saveMessage(Message message) {
		messageDAO.insertMessage(message);
	}

}
