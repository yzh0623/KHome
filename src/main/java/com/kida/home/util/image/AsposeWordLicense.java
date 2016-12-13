package com.kida.home.util.image;

import java.io.InputStream;

import com.aspose.words.License;

public class AsposeWordLicense {

	public static boolean getLicense() {
		boolean result = false;
		try {
			InputStream is = AsposeWordLicense.class.getClassLoader()
					.getResourceAsStream("aspose/word/license/license.xml");
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
