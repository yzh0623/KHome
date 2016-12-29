package com.kida.home.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.kida.home.util.properties.ConfigProperty;

@Component
public class FileIOUtils {

	private static final Logger LOG = Logger.getLogger(FileIOUtils.class);

	@Resource(name = "configProperty")
	private ConfigProperty configProperty;

	private String extractFilePath;
	private Path filePath;

	private void initParam() {
		extractFilePath = configProperty.getExtractFilePath();
		filePath = Paths.get(extractFilePath);
	}

	/**
	 * 生成文档
	 * 
	 * @param paramMap
	 */
	public void generateFiles(Map<String, String> paramMap) {
		initParam();
		try {
			if (!paramMap.isEmpty()) {
				deleteAllFile();

				String key;
				String value;

				Path path;
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					key = entry.getKey().replace("\\", "、").replace("/", " ");
					value = entry.getValue();
					path = createFile(key);
					writeFile(path, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info(e.getStackTrace());
		}
	}

	/**
	 * 获取爬虫文档内容
	 * 
	 * @return
	 */
	public Map<String, String> getExtractFileContent() {
		initParam();

		Map<String, String> reMap = new HashMap<>();

		File file = new File(extractFilePath);
		File[] files = file.listFiles();

		if (files.length > 0) {
			for (File f : files) {
				try {
					Path path = f.toPath();
					String key = path.getFileName().toString()
							.replace(".txt", "").replace("、", "\\");
					String value = readFile(path);
					reMap.put(key, value);
				} catch (IOException e) {
					LOG.info(e.getStackTrace());
				}
			}
		}
		return reMap;
	}

	/**
	 * 遍历并删除所有文件
	 * 
	 * @throws IOException
	 */
	private void deleteAllFile() throws IOException {
		DirectoryStream stream = Files.newDirectoryStream(filePath);
		Iterator iterator = stream.iterator();
		while (iterator.hasNext()) {
			Path path = (Path) iterator.next();
			Files.deleteIfExists(path);
		}
		stream.close();
	}

	/**
	 * 创建文档
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	private Path createFile(String key) throws IOException {
		Path newPath = Paths.get(extractFilePath + "/" + key + ".txt");
		return Files.createFile(newPath);
	}

	/**
	 * 写入信息
	 * 
	 * @param path
	 * @param value
	 * @throws IOException
	 */
	private void writeFile(Path path, String value) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(path,
				StandardCharsets.UTF_8);
		writer.write(value);
		writer.flush();
		writer.close();
	}

	/**
	 * 读取文件内容
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private String readFile(Path path) throws IOException {
		String str;
		StringBuilder sb = new StringBuilder("");

		BufferedReader reader = Files.newBufferedReader(path,
				StandardCharsets.UTF_8);
		while ((str = reader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		return sb.toString();
	}
}
