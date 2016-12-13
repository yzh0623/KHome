package com.kida.home.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kida.home.bean.Updated;
import com.kida.home.dao.UpdatedDAO;
import com.kida.home.service.UpdatedService;
import com.kida.home.util.UUIDGenerator;

@Service
public class UpdatedServiceImpl implements UpdatedService {

	@Autowired
	private UpdatedDAO updatedDAO;

	@Override
	public List<Updated> queryLastestDayUpdate() {
		return updatedDAO.queryLastestDayUpdate();
	}

	@Override
	public List<Updated> queryNextDay(String oldDay) {
		return updatedDAO.queryNextDay(oldDay);
	}

	@Override
	public String saveUpdateInfo(String updateInfo) {
		Updated updated = new Updated();
		updated.setUpdatedId(UUIDGenerator.uuidGenerator(16));
		updated.setUpdateInfo(updateInfo);
		updated.setCreateTime(new Date());
		int counter = updatedDAO.insertUpdated(updated);
		return String.valueOf(counter);
	}

}
