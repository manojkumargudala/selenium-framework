package com.demoqa.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendEmail {
  public static void sendEmailThis(final String filePath) throws IOException, EmailException {
    File file = new File(System.getProperty("user.dir") + filePath);
    String string = FileUtils.readFileToString(file);
    HtmlEmail email = new HtmlEmail();
    email.setHostName("smtp.gmail.com");
    email.setSmtpPort(465);
    email.setAuthenticator(new DefaultAuthenticator("seleniumauto0@gmail.com", "manoj1234"));
    email.setSSLOnConnect(true);
    email.setFrom("seleniumauto0@gmail.com");
    email.setSubject("TestMail- Alternative message");
    email.setHtmlMsg(string);
    email.addTo("shafikshafik@gmail.com");
    email.send();
  }

  public static void main(final String[] args) {
    try {
      SendEmail.sendEmailThis(
          "C:\\Users\\xprk661\\prokarmamiddleware\\sampleselenium\\test-output\\emailable-report.html");
    } catch (IOException | EmailException e) {
      e.printStackTrace();
    }
  }
}
