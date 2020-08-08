package com.storedemoqa.testcases;

import com.demoqa.listeners.ZipAndSendMail;

public class TestSendEmail {
	public static void main(String[] args) {
		ZipAndSendMail andSendMail = new ZipAndSendMail();
		andSendMail.sendEmail("results-folder");
	}
}
