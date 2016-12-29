package com.kida.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kida.home.bean.vo.ArticleVO;
import com.kida.home.bean.vo.BriefPicVO;
import com.kida.home.service.ArticleService;
import com.kida.home.service.BriefPicService;
import com.kida.home.util.CommonUtils;
import com.kida.home.util.FileIOUtils;
import com.kida.home.util.properties.ConfigProperty;

/**
 * 文章控制类 spring controller
 * 
 * @author kida
 *
 */
@Controller
@Singleton
@Path("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private BriefPicService briefPicService;

	@Autowired
	private FileIOUtils fileIOUtils;

	@Resource(name = "configProperty")
	private ConfigProperty configProperty;

	/**
	 * 用户点击加载下一行信息
	 * 
	 * @param lastLocation
	 *            最后显示文章所处行数
	 * @return
	 */
	@POST
	@Path("/nextrow")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String nextRow(String lastLocation) {
		List<ArticleVO> articleLIst = articleService
				.queryNextRowArticle(lastLocation);
		return CommonUtils.callJsonBack("blockList", articleLIst);
	}

	/**
	 * 显示首页块内容信息
	 * 
	 * @return
	 */
	@POST
	@Path("/blocklist")
	@Produces(MediaType.APPLICATION_JSON)
	public String blockList() {
		String blockShow = configProperty.getBlockShow();
		List<ArticleVO> articleLIst = articleService
				.queryArticle4Show(blockShow);
		return CommonUtils.callJsonBack("blockList", articleLIst);
	}

	/**
	 * 查询logs侧栏中数据
	 * 
	 * @return
	 */
	@POST
	@Path("/logslist")
	@Produces(MediaType.APPLICATION_JSON)
	public String logsList() {
		List<ArticleVO> articleLIst = articleService.queryLogsArticleLine();
		return CommonUtils.callJsonBack("logsList", articleLIst);
	}

	/**
	 * 查询logs侧栏中数据
	 * 
	 * @return
	 */
	@POST
	@Path("/experlist")
	@Produces(MediaType.APPLICATION_JSON)
	public String experList() {
		List<ArticleVO> articleLIst = articleService.queryExperArticleLine();
		return CommonUtils.callJsonBack("experList", articleLIst);
	}

	/**
	 * 获取文章详细信息
	 * 
	 * @param id
	 * @return
	 */
	@POST
	@Path("/detailinfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String detailInfo(String id) {
		Map<String, Object> reMap = new HashMap<>();
		List<BriefPicVO> briefPicVOList = briefPicService
				.queryBriefPicVOByArticleId(id);
		reMap.put("briefPicVOList", briefPicVOList);
		return CommonUtils.callJsonBack("reMap", reMap);
	}

	/**
	 * 查询类型列表
	 * 
	 * @param cateType
	 *            查询类型
	 * @param nowLocation
	 *            当前行数
	 * @return
	 */
	@POST
	@Path("/categorylist/{cateType}/{nowLocation}")
	@Produces(MediaType.APPLICATION_JSON)
	public String categoryList(@PathParam("cateType") String cateType,
			@PathParam("nowLocation") String nowLocation) {
		List<ArticleVO> articleLIst = articleService
				.queryArticleByCate(cateType, nowLocation);
		return CommonUtils.callJsonBack("cateList", articleLIst);
	}

	/**
	 * 标签列表
	 * 
	 * @param keyword
	 * @return
	 */
	@POST
	@Path("/labelList/{keyword}/{nowLocation}")
	@Produces(MediaType.APPLICATION_JSON)
	public String labelList(@PathParam("keyword") String keyword,
			@PathParam("nowLocation") String nowLocation) {
		List<ArticleVO> articleLIst = articleService
				.queryArticleByLabel(keyword, nowLocation);
		return CommonUtils.callJsonBack("cateList", articleLIst);
	}

	/**
	 * 获取弹幕信息
	 * 
	 * @param type
	 * @return
	 */
	@POST
	@Path("/barragerMap")
	@Produces(MediaType.APPLICATION_JSON)
	public String barragerMap() {
		Map<String, String> reMap = fileIOUtils.getExtractFileContent();
		return CommonUtils.callJsonBack("danmuMap", reMap);
	}

}
