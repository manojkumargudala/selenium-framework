package com.demoqa.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DataUtils {

	private static Logger log = Logger.getLogger(DataUtils.class);

	public static String getRandomNumber() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SS");
		return ft.format(new Date());
	}

	private static String getRandomCaptureFileNamePath(final String testName) {
		String fileName = getRandomNumber();
		StringBuffer stringTotalName = new StringBuffer();
		stringTotalName.append(GenericConstants.SCREEN_SHOT_FOLDER + File.separator);
		stringTotalName.append(testName);
		stringTotalName.append("-");
		stringTotalName.append(fileName);
		return stringTotalName.toString();

	}

	public static void pause(final int seconds) {
		Date start = new Date();
		log.info("Started Sleeping at " + start.toString());
		Date end = new Date();
		long part = end.getTime() + 10000;
		int i = 0;
		// log.info("Sleeping for " + DurationFormatUtils.formatDurationWords(seconds *
		// 1000, true, false));
		while (end.getTime() - start.getTime() < seconds * 1000) {
			if (part - end.getTime() <= 0) {
				part = end.getTime() + 10000;
				i++;
				System.out.print(" . ");
			}
			if (i == 6) {
				log.info("60 seconds completed Going to sleep again, Do some thing else .. !!!");
				i = 0;
			}
			end = new Date();
		}
		log.info("Awakee sleeping is done ... !!!!");
		log.info("Started Sleeping at " + end.toString());
	}

	public static String getRandomCaptureFileName(final String testName) {
		StringBuffer stringTotalName = new StringBuffer();
		stringTotalName.append(getRandomCaptureFileNamePath(testName));
		stringTotalName.append(".png");
		return stringTotalName.toString();

	}

	public static boolean stringToBoolean(final String property) {
		if (property == null)
			return false;
		else if (property.equalsIgnoreCase("true"))
			return true;
		else
			return Boolean.getBoolean(property);
	}

	public static String getRandomNumberWithReadableFormat() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		return ft.format(new Date());
	}
}
