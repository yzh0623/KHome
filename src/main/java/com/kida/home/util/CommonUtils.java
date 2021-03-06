package com.kida.home.util;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 工具类
 * 
 * @author kida
 *
 */
@Component
public class CommonUtils {

	private CommonUtils() {

	}

	/**
	 * 返回JSONString输出
	 * 
	 * @param mapKey
	 *            JSONObject key
	 * @param obj
	 *            传送内容
	 * @return
	 */
	public static String callJsonBack(String mapKey, Object obj) {
		JSONObject reObject = new JSONObject();
		reObject.put(mapKey, obj);
		return reObject.toJSONString();
	}
}
