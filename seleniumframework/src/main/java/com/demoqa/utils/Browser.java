package com.demoqa.utils;

import java.util.Random;

public enum Browser {
	Chrome, IE, Firefox;

	public static Browser getRandomBrowser() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
