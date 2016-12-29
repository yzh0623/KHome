package com.kida.home.util.image;

import java.io.InputStream;

import com.aspose.words.License;

public class AsposeWordLicense {

	private AsposeWordLicense() {

	}

	public static boolean getLicense() throws Exception {
		InputStream is = AsposeWordLicense.class.getClassLoader()
				.getResourceAsStream("aspose/word/license/license.xml");
		License aposeLic = new License();
		aposeLic.setLicense(is);
		return true;
	}
}
