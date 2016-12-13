package com.kida.home.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("statusProperty")
public class StatusProperty {

	@Value("#{status[noConvent]}")
	private String noConvent;

	@Value("#{status[cannotCreateDir]}")
	private String cannotCreateDir;

	@Value("#{status[cannotConventDoc2Image]}")
	private String cannotConventDoc2Image;

	@Value("#{status[success2Convent2Image]}")
	private String success2Convent2Image;

	@Value("#{status[success2SaveFile]}")
	private String success2SaveFile;

	@Value("#{status[cannotSaveFile]}")
	private String cannotSaveFile;

	@Value("#{status[finish2Archive]}")
	private String finish2Archive;

	@Value("#{status[cannotMove2Archive]}")
	private String cannotMove2Archive;

	@Value("#{status[cannotCloseNIOStream]}")
	private String cannotCloseNIOStream;

	public String getCannotMove2Archive() {
		return cannotMove2Archive;
	}

	public void setCannotMove2Archive(String cannotMove2Archive) {
		this.cannotMove2Archive = cannotMove2Archive;
	}

	public String getCannotCloseNIOStream() {
		return cannotCloseNIOStream;
	}

	public void setCannotCloseNIOStream(String cannotCloseNIOStream) {
		this.cannotCloseNIOStream = cannotCloseNIOStream;
	}

	public String getFinish2Archive() {
		return finish2Archive;
	}

	public void setFinish2Archive(String finish2Archive) {
		this.finish2Archive = finish2Archive;
	}

	public String getCannotSaveFile() {
		return cannotSaveFile;
	}

	public void setCannotSaveFile(String cannotSaveFile) {
		this.cannotSaveFile = cannotSaveFile;
	}

	public String getSuccess2SaveFile() {
		return success2SaveFile;
	}

	public void setSuccess2SaveFile(String success2SaveFile) {
		this.success2SaveFile = success2SaveFile;
	}

	public String getSuccess2Convent2Image() {
		return success2Convent2Image;
	}

	public void setSuccess2Convent2Image(String success2Convent2Image) {
		this.success2Convent2Image = success2Convent2Image;
	}

	public String getCannotConventDoc2Image() {
		return cannotConventDoc2Image;
	}

	public void setCannotConventDoc2Image(String cannotConventDoc2Image) {
		this.cannotConventDoc2Image = cannotConventDoc2Image;
	}

	public String getCannotCreateDir() {
		return cannotCreateDir;
	}

	public void setCannotCreateDir(String cannotCreateDir) {
		this.cannotCreateDir = cannotCreateDir;
	}

	public String getNoConvent() {
		return noConvent;
	}

	public void setNoConvent(String noConvent) {
		this.noConvent = noConvent;
	}

}
