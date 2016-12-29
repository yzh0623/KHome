package com.kida.home.util;

import java.util.Random;

/**
 * 自己生成16位uuid序列号工具
 * 
 * @author kida
 *
 */
public class UUIDGenerator {
	private static final char[] digits = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	private UUIDGenerator() {

	}

	public static String uuidGenerator(int baseBit) {
		Random random = new Random();
		char[] cs = new char[baseBit];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = digits[random.nextInt(digits.length)];
		}
		return new String(cs);
	}

}
