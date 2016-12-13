package com.kida.home.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kida.home.bean.Updated;
import com.kida.home.service.UpdatedService;
import com.kida.home.util.CommonUtils;
import com.kida.home.util.properties.ConfigProperty;

@Controller
@Singleton
@Path("/updated")
public class UpdatedController {

	@Autowired
	private UpdatedService updatedService;

	@Resource(name = "configProperty")
	private ConfigProperty configProperty;

	@POST
	@Path("/lastestDayUpdateList")
	@Produces(MediaType.APPLICATION_JSON)
	public String lastestDayUpdateList() {
		List<Updated> updatedLIst = updatedService.queryLastestDayUpdate();
		return CommonUtils.callJsonBack("updatedLIst", updatedLIst);
	}

	@POST
	@Path("/showNextDay")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String showNextDay(String oldDay) {
		List<Updated> updatedLIst = updatedService.queryNextDay(oldDay);
		return CommonUtils.callJsonBack("updatedLIst", updatedLIst);
	}

	@POST
	@Path("/saveUpdated")
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveUpdated(String updateInfo) {
		return updatedService.saveUpdateInfo(updateInfo);
	}

	@POST
	@Path("/checkPwd")
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkPwd(String pwd) {
		String reVal = "block";
		if (configProperty.getPwd().equals(pwd)) {
			reVal = "pass";
		}
		return CommonUtils.callJsonBack("reVal", reVal);
	}
}
