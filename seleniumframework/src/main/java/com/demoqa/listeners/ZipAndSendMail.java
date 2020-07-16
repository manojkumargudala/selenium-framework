package com.demoqa.listeners;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class ZipAndSendMail {
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	static String[] toEmails = { "manoj.kumar.gudala@gmail.com" };
	static String fromUser1 = "tsutiger129@gmail.com";
	static String password1 = "AccountFor@tsu";
	static String fromUser = "seleniumauto0@gmail.com";
	static String password = "manoj1234";

	public void sendEmail(String reportFolder) {
		// report folder - extent reports
		try {

			File dir = new File(reportFolder);
			File[] files = dir.listFiles();
			File lastModified = Arrays.stream(files).filter(File::isDirectory)
					.max(Comparator.comparing(File::lastModified)).orElse(null);
			System.out.println("Trying to zip" + lastModified.getName());

			Zip.zipDir(reportFolder, reportFolder + ".zip");

			Mail javaEmail = new Mail();

			javaEmail.setMailServerProperties();

			javaEmail.createEmailMessage("API Testing Reports", // subject
					"Please find the reports in attachment.", // body
					reportFolder + "\\" + lastModified.getName() + ".zip", // attachment
																			// path
					"Reports.zip", // name of attachment
					toEmails// receivers
			);
			javaEmail.sendEmail(fromUser, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}