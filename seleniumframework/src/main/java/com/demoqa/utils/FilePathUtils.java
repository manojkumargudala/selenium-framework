package com.demoqa.utils;

import java.io.File;

public class FilePathUtils {
	public static String getFullPathOfFileFromClassPath(final String browserImplExeFileName) {
		File file = new File(FilePathUtils.class.getClassLoader().getResource(browserImplExeFileName).getFile());
		return file.getPath();
	}

	public static String getScreenShotFullPath(final String fileName) {
		StringBuffer stringTotalName = new StringBuffer();
		stringTotalName.append(System.getProperty("user.dir"));
		stringTotalName.append("/" + fileName);
		return stringTotalName.toString();

	}

	public static String getScreenCastFolderPath(final String folderName) {
		StringBuffer stringTotalName = new StringBuffer();
		stringTotalName.append(System.getProperty("user.dir"));
		stringTotalName.append("\\ScreenCast\\");
		stringTotalName.append("\\" + folderName);
		return stringTotalName.toString();

	}

}
