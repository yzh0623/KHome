package com.kida.home.util;

public class TextUtil {
	private TextUtil() {

	}

	public static boolean isEmpty(String url) {
		if (null != url && url.trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}
}
