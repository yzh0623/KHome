package com.kida.home.controller;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kida.home.bean.DicCommon;
import com.kida.home.service.DicCommonService;
import com.kida.home.util.CommonUtils;

@Controller
@Singleton
@Path("/dicCommon")
public class DicCommonController {

	@Autowired
	private DicCommonService dicCommonService;

	@POST
	@Path("/labelDicList")
	@Produces(MediaType.APPLICATION_JSON)
	public String categoryList(String groupId) {
		List<DicCommon> dicCommonLIst = dicCommonService
				.queryDicCommonByGroupId(groupId);
		return CommonUtils.callJsonBack("dicCommonLIst", dicCommonLIst);
	}
}
