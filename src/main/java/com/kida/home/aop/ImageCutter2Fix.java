package com.kida.home.aop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;

import com.kida.home.bean.BriefPic;
import com.kida.home.service.BriefPicService;
import com.kida.home.util.image.ImageCutterUtils;

//@Aspect
//@Component
public class ImageCutter2Fix {

	public static final String CutPointExec = "execution(* com.kida.home.service.impl.ContentConverServiceImpl.doIt(..))";

	// 起始坐标，剪切大小
	private static int x = 0;
	private static int y = 165;
	private static int width = 1075;
	private static int height = 1531;

	// 裁剪范围大小
	private static final int clientWidth = 1075;
	private static final int clientHeight = 1721;

	@Autowired
	private BriefPicService briefPicService;

	@After(CutPointExec)
	public void fixTheImageAfterGenrate(JoinPoint joinPoint) {

		List<BriefPic> bpList = briefPicService.queryBriefPicByCutStatus(0);

		if (null != bpList && !bpList.isEmpty()) {
			for (BriefPic bp : bpList) {
				getImageCutterPoint(bp.getPicPath());
				ImageCutterUtils.cutImage(bp.getPicPath(), bp.getPicPath(), x,
						y, width, height);

				bp.setCutStatus(1);
				briefPicService.updateBriefPic(bp);
			}
		}
	}

	private void getImageCutterPoint(String filePath) {
		try {
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);
			double destWidth = image.getWidth();
			double destHeight = image.getHeight();

			if (destWidth < width || destHeight < height)
				throw new Exception("源图大小小于截取图片大小!");

			double widthRatio = destWidth / clientWidth;
			double heightRatio = destHeight / clientHeight;

			x = Double.valueOf(x * widthRatio).intValue();
			y = Double.valueOf(y * heightRatio).intValue();
			width = Double.valueOf(width * widthRatio).intValue();
			height = Double.valueOf(height * heightRatio).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
