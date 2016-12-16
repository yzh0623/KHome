package com.kida.home.util.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageCutterUtils {

	private ImageCutterUtils() {

	}

	/**
	 * 获取图片格式
	 * 
	 * @param file
	 *            图片文件
	 * @return 图片格式
	 */
	public static String getImageFormatName(File file) throws IOException {
		String formatName = null;

		ImageInputStream iis = ImageIO.createImageInputStream(file);
		Iterator<ImageReader> imageReader = ImageIO.getImageReaders(iis);
		if (imageReader.hasNext()) {
			ImageReader reader = imageReader.next();
			formatName = reader.getFormatName();
		}

		return formatName;
	}

	/**
	 * 剪切图片
	 *
	 * @param source
	 *            待剪切图片路径
	 * @param targetPath
	 *            裁剪后保存路径（默认为源路径）
	 * @param x
	 *            起始横坐标
	 * @param y
	 *            起始纵坐标
	 * @param width
	 *            剪切宽度
	 * @param height
	 *            剪切高度
	 *
	 * @returns 裁剪后保存路径（图片后缀根据图片本身类型生成）
	 * @throws IOException
	 */
	public static String cutImage(String sourcePath, String targetPath, int x,
			int y, int width, int height) {
		try {
			File file = new File(sourcePath);
			if (!file.exists()) {
				throw new IOException("not found the image：" + sourcePath);
			}
			if (null == targetPath || targetPath.isEmpty())
				targetPath = sourcePath;

			String formatName = getImageFormatName(file);
			if (null == formatName)
				return targetPath;
			formatName = formatName.toLowerCase();

			// 防止图片后缀与图片本身类型不一致的情况
			String pathPrefix = getPathWithoutSuffix(targetPath);
			targetPath = pathPrefix + formatName;

			BufferedImage image = ImageIO.read(file);
			image = image.getSubimage(x, y, width, height);
			ImageIO.write(image, formatName, new File(targetPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetPath;
	}

	/**
	 * 压缩图片
	 * 
	 * @param sourcePath
	 *            待压缩的图片路径
	 * @param targetPath
	 *            压缩后图片路径（默认为初始路径）
	 * @param width
	 *            压缩宽度
	 * @param height
	 *            压缩高度
	 *
	 * @returns 裁剪后保存路径（图片后缀根据图片本身类型生成）
	 * @throws IOException
	 */
	public static String zoom(String sourcePath, String targetPath, int width,
			int height) throws IOException {
		File file = new File(sourcePath);
		if (!file.exists()) {
			throw new IOException("not found the image ：" + sourcePath);
		}
		if (null == targetPath || targetPath.isEmpty())
			targetPath = sourcePath;

		String formatName = getImageFormatName(file);
		if (null == formatName)
			return targetPath;
		formatName = formatName.toLowerCase();

		// 防止图片后缀与图片本身类型不一致的情况
		String pathPrefix = getPathWithoutSuffix(targetPath);
		targetPath = pathPrefix + formatName;

		BufferedImage image = ImageIO.read(file);
		BufferedImage zoomImage = zoom(image, width, height);
		ImageIO.write(zoomImage, formatName, new File(targetPath));

		return targetPath;
	}

	/**
	 * 获取不包含后缀的文件路径
	 *
	 * @param src
	 * @return
	 */
	public static String getPathWithoutSuffix(String src) {
		String path = src;
		int index = path.lastIndexOf(".");
		if (index > 0) {
			path = path.substring(0, index + 1);
		}
		return path;
	}

	/**
	 * 压缩图片
	 * 
	 * @param sourceImage
	 *            待压缩图片
	 * @param width
	 *            压缩图片高度
	 * @param heigt
	 *            压缩图片宽度
	 */
	private static BufferedImage zoom(BufferedImage sourceImage, int width,
			int height) {
		BufferedImage zoomImage = new BufferedImage(width, height,
				sourceImage.getType());
		Image image = sourceImage.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		Graphics gc = zoomImage.getGraphics();
		gc.setColor(Color.WHITE);
		gc.drawImage(image, 0, 0, null);
		return zoomImage;
	}
}
