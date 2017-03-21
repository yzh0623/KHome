package com.kida.home.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.Node;
import com.aspose.words.SaveFormat;
import com.kida.home.bean.Article;
import com.kida.home.bean.ArticleLabel;
import com.kida.home.bean.BriefPic;
import com.kida.home.bean.DicCommon;
import com.kida.home.bean.Message;
import com.kida.home.bean.vo.MessageVO;
import com.kida.home.service.ArticleLabelService;
import com.kida.home.service.ArticleService;
import com.kida.home.service.BriefPicService;
import com.kida.home.service.ContentConverService;
import com.kida.home.service.DicCommonService;
import com.kida.home.service.MessageService;
import com.kida.home.util.UUIDGenerator;
import com.kida.home.util.image.AsposeWordLicense;
import com.kida.home.util.properties.ConfigProperty;
import com.kida.home.util.properties.StatusProperty;

@Service
public class ContentConverServiceImpl implements ContentConverService {

	/**
	 * 获取properties文件内容
	 */
	@Resource(name = "configProperty")
	private ConfigProperty configProperty;
	@Resource(name = "statusProperty")
	private StatusProperty statusProperty;

	/**
	 * 预加载需要的接口
	 */
	@Autowired
	private BriefPicService briefPicService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ArticleLabelService articleLabelService;
	@Autowired
	private DicCommonService dicCommonService;

	/**
	 * 静态全局变量
	 */
	private String archivePath;// 归档路径
	private String imagePath;// 图片路径

	/**
	 * 定时器入口
	 */
	@Override
	public void doIt() {
		MessageVO mvo = conventFile2Image();
		Message msg = new Message();
		// 返回的信息类通过BeanUtils进行实体之间的数据转换
		BeanUtils.copyProperties(mvo, msg);
		msg.setMsgId(UUIDGenerator.uuidGenerator(16));
		messageService.saveMessage(msg);
	}

	/**
	 * 开始转换doc文档
	 * 
	 * @return
	 */
	private MessageVO conventFile2Image() {
		MessageVO mvo = new MessageVO();
		// 扫描获取当前需要转换的文档
		File[] fileArray = scanAndGetNeed2ConventFile();
		if (fileArray.length > 0) {
			// 转换文档并创建图片
			mvo = conventFileAndCreateImage(fileArray, mvo);
		} else {
			mvo.setMsgCode("noConvent");
			mvo.setMsgInfo(statusProperty.getNoConvent());
		}
		return mvo;
	}

	/**
	 * 扫描并获取需要转换的文件
	 * 
	 * @return
	 */
	private File[] scanAndGetNeed2ConventFile() {
		String need2ConventFilePath = configProperty.getNowDocPath();
		File n2cDir = new File(need2ConventFilePath);
		return n2cDir.listFiles();
	}

	/**
	 * 创建归档文件并转换成图片
	 * 
	 * @param fileArra
	 * @param mvo
	 * @return
	 */
	private MessageVO conventFileAndCreateImage(File[] fileArra, MessageVO mvo) {
		String archiveDocPath = configProperty.getArchiveDocPath();

		// 通过check4Dir来查找当前路径下是否存在文件夹，若没有则新建一个文件夹，并将路径返回到静态全局变量中
		Map<String, Object> checkResult = check4Dir(mvo, archiveDocPath);
		boolean checkFlag = (Boolean) checkResult.get("flag");
		MessageVO checkMVO = (MessageVO) checkResult.get("mvo");
		archivePath = String.valueOf(checkResult.get("path"));

		// 若已经有文件夹了之后，同理检查图片那边是否有对应的文件夹
		if (checkFlag) {
			String _imagePath = configProperty.getImagePath();
			Map<String, Object> checkImageResult = check4Dir(mvo, _imagePath);
			boolean checkImageFlag = (Boolean) checkImageResult.get("flag");
			MessageVO checkImageMVO = (MessageVO) checkImageResult.get("mvo");
			imagePath = String.valueOf(checkImageResult.get("path"));

			// 若图片那边也有了，则开始转换图片
			if (checkImageFlag) {
				return createImagesAndArchiveFiles(fileArra, checkImageMVO);
			} else {
				return checkImageMVO;
			}
		} else {
			return checkMVO;
		}
	}

	/**
	 * 检查是否存在归档目录，若存在则返回，若不存在则新建后返回
	 * 
	 * @param mvo
	 * @return
	 */
	private Map<String, Object> check4Dir(MessageVO mvo, String path) {
		Map<String, Object> reMap = new HashMap<String, Object>();

		boolean flag = false;

		String todayPath = getTodayPath(path);
		try {
			// 查询改目录是否存在
			File adDir = new File(todayPath);
			if (!adDir.exists()) {
				flag = adDir.mkdirs();
			} else {
				flag = true;
			}
		} catch (Exception e) {
			mvo.setMsgCode("cannotCreateDir");
			mvo.setMsgInfo(statusProperty.getCannotCreateDir() + " Exception: " + e.getStackTrace());
		}

		reMap.put("flag", flag);
		reMap.put("mvo", mvo);
		reMap.put("path", todayPath);
		return reMap;
	}

	/**
	 * 获取今天的归档目录路径
	 * 
	 * @return
	 */
	private String getTodayPath(String path) {
		// 获取当前日期
		Calendar now = Calendar.getInstance();
		String year = String.valueOf(now.get(Calendar.YEAR));
		String month = String.valueOf(now.get(Calendar.MONTH) + 1);
		String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));

		// 加入日期拼接归档目录路径
		StringBuilder todayPath = new StringBuilder("").append(path).append("/").append(year).append("/").append(month)
				.append("/").append(day);
		return todayPath.toString();
	}

	/**
	 * 创建图片并生成归档文件
	 * 
	 * @param fileArra
	 * @param mvo
	 * @return
	 */
	public MessageVO createImagesAndArchiveFiles(File[] fileArra, MessageVO mvo) {
		String articleId;
		Map<String, Object> paramMap;
		// 遍历需要转换的文件
		for (File file : fileArra) {
			// 解析文档并保存数据到数据库中，最后返回文章id
			paramMap = analysisFileAndSave(file, mvo);
			mvo = (MessageVO) paramMap.get("mvo");
			articleId = String.valueOf(paramMap.get("articleId"));

			// 通过文章id和文件创建图片
			mvo = convent2ImageAndSave(file, mvo, articleId);

			// 若图片创建成功，则可以将文件归档
			if ("success2Convent2Image".equals(mvo.getMsgCode())) {
				mvo = moveFile2Archive(file, mvo);
			}
		}
		return mvo;
	}

	/**
	 * 分析并保存文章信息
	 * 
	 * @param file
	 * @param mvo
	 * @return
	 */
	private Map<String, Object> analysisFileAndSave(File file, MessageVO mvo) {
		Map<String, Object> reMap = new HashMap<String, Object>();

		String articleId = null;
		String category = null;
		String summary = null;
		String hidden = null;

		String[] parts = null;
		String[] labels = null;

		String title = file.getName().substring(0, file.getName().lastIndexOf('.'));

		try {
			// 通过aspose-word获取doc文档的类型和摘要
			Document doc = new Document(file.getAbsolutePath());
			Node[] nodeArr = doc.getFirstSection().getBody().getChildNodes().toArray();
			// 类型隐藏在标题后面，通过正则表达式获取类型
			String txtStr = nodeArr[0].getText();
			if (txtStr.indexOf("Evaluation Only") > -1) {
				hidden = nodeArr[2].getText().split("[()]")[1].toUpperCase();
				// 摘要永远放在标题下面
				summary = nodeArr[3].getText();
			} else {
				hidden = nodeArr[1].getText().split("[()]")[1].toUpperCase();
				// 摘要永远放在标题下面
				summary = nodeArr[2].getText();
			}
			parts = hidden.split("/");
			category = parts[0];
			labels = parts[1].split("@#@");

			Article article = articleService.queryArticleByTitle(title);
			if (null == article) {
				article = new Article();
				articleId = UUIDGenerator.uuidGenerator(16);
				article.setArticleId(articleId);
				article.setReadCount(0);
				article.setCreateTime(new Date());
				article.setTitle(title);
			} else {
				articleId = article.getArticleId();
			}
			// 保存文章的主题信息
			article.setBrief(summary);
			article.setCategory(category);
			article.setSummary(summary);

			articleService.saveArticle(article);

			if (labels.length > 0) {
				List<ArticleLabel> aLabelList = new ArrayList<ArticleLabel>();
				ArticleLabel al;

				for (String lStr : labels) {
					al = articleLabelService.queryArticleLabelByArticleIdAndLabel(articleId, lStr);
					if (null == al) {
						al = new ArticleLabel();
						al.setArticleId(articleId);
						al.setLabel(lStr);
						aLabelList.add(al);
					}

					DicCommon dc = dicCommonService.queryDicCommonByGroupIdAndCode("labelKeyWord", lStr);
					if (null == dc) {
						dc = new DicCommon();
						dc.setDicId(UUIDGenerator.uuidGenerator(16));
						dc.setGroupId("labelKeyWord");
						dc.setCode(lStr);
						dc.setComment(lStr);

						dicCommonService.saveDicCommon(dc);
					}
				}

				for (ArticleLabel articleLabel : aLabelList) {
					articleLabelService.saveArticleLabel(articleLabel);
				}
			}

			mvo.setMsgCode("success2SaveFile");
			mvo.setMsgInfo(statusProperty.getSuccess2SaveFile() + " fileName is :" + title);
		} catch (Exception ioe) {
			mvo.setMsgCode("cannotSaveFile");
			mvo.setMsgInfo(statusProperty.getCannotSaveFile() + " Exception: " + ioe.getStackTrace());
		}

		reMap.put("mvo", mvo);
		reMap.put("articleId", articleId);
		return reMap;
	}

	/**
	 * 转换图片后保存数据
	 * 
	 * @param file
	 * @param mvo
	 * @param articleId
	 * @return
	 */
	private MessageVO convent2ImageAndSave(File file, MessageVO mvo, String articleId) {
		boolean flag = false;
		BriefPic briefPic = null;
		String _imagePath = imagePath + File.separator + articleId;
		try {
			// 创建针对该文章id对应的文件夹
			File dir = new File(_imagePath);
			if (!dir.exists()) {
				flag = dir.mkdir();
			} else {
				flag = true;
				File[] files = dir.listFiles();
				if (files.length > 0) {
					for (File _file : files) {
						_file.delete();
					}
				}
			}
			// 创建成功后开始转换图片
			if (flag) {
				if (AsposeWordLicense.getLicense()) {
					briefPicService.deleteBriefPicByArticleId(articleId);

					Document doc = new Document(file.getAbsolutePath());
					ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
					options.setResolution(130);
					options.setUseHighQualityRendering(false);
					for (int i = 0; i < doc.getPageCount(); i++) {
						String fileId = UUIDGenerator.uuidGenerator(16);
						String imageFilePath = _imagePath + File.separator + "brief_" + i + ".png";
						options.setPageIndex(i);
						doc.save(imageFilePath, options);

						// 图片保存完毕后保存路径到数据库
						briefPic = new BriefPic();
						briefPic.setArticleId(articleId);
						briefPic.setPicId(fileId);
						briefPic.setPicPath(imageFilePath);
						briefPic.setSortId(i);
						briefPic.setCutStatus(0);
						briefPicService.insertBriefPic(briefPic);
					}
				}
			}
		} catch (Exception ioe) {
			mvo.setMsgCode("cannotConventDoc2Image");
			mvo.setMsgInfo(statusProperty.getCannotConventDoc2Image() + " Exception: " + ioe.getStackTrace());
		}
		mvo.setMsgCode("success2Convent2Image");
		mvo.setMsgInfo(statusProperty.getSuccess2Convent2Image());
		return mvo;

	}

	/**
	 * 将文档移动到归档目录
	 * 
	 * @param file
	 */
	@SuppressWarnings("resource")
	private MessageVO moveFile2Archive(File file, MessageVO mvo) {
		FileChannel sourceCh = null;
		FileChannel destCh = null;
		try {
			// 使用nio方式复制文件到指定目录
			sourceCh = new FileInputStream(file.getAbsolutePath()).getChannel();
			destCh = new FileOutputStream(archivePath + File.separator + file.getName()).getChannel();

			MappedByteBuffer mbb = sourceCh.map(FileChannel.MapMode.READ_ONLY, 0, sourceCh.size());
			destCh.write(mbb);

			clean(mbb);
			// 操作完成后返回操作信息
			mvo.setMsgCode("finish2Archive");
			mvo.setMsgInfo(statusProperty.getFinish2Archive());
		} catch (Exception e) {
			mvo.setMsgCode("cannotMove2Archive");
			mvo.setMsgInfo(statusProperty.getCannotMove2Archive() + " Exception: " + e.getStackTrace());
		} finally {
			try {
				if (null != sourceCh)
					sourceCh.close();

				if (null != destCh)
					destCh.close();

				Files.deleteIfExists(file.toPath());
			} catch (IOException e) {
				mvo.setMsgCode("cannotCloseNIOStream");
				mvo.setMsgInfo(statusProperty.getCannotCloseNIOStream() + " Exception: " + e.getStackTrace());
			}
		}
		return mvo;
	}

	/**
	 * 内存映射文件操作完内存以后,还持有文件的句柄,所以如果你需要删除的话就是失败的 所以需要清除buffer
	 * 
	 * @param buffer
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static void clean(final Object buffer) throws Exception {
		AccessController.doPrivileged(new PrivilegedAction() {
			@Override
			public Object run() {
				try {
					Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
					cleaner.clean();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});

	}
}
