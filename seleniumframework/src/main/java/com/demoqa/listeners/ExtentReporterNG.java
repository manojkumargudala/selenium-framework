package com.demoqa.listeners;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.demoqa.utils.DataUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentReporterNG implements IReporter {
  private ExtentReports extent;
  Logger log = Logger.getLogger(ExtentReporterNG.class);

  @Override
  public void generateReport(final List<XmlSuite> xmlSuites, final List<ISuite> suites,
      final String outputDirectory) {
    extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true,
        NetworkMode.ONLINE);
    File extenrReportConfig = new File("src/main/resources/extentreports.xml");
    extent.loadConfig(extenrReportConfig);
    for (ISuite suite : suites) {
      Map<String, ISuiteResult> result = suite.getResults();
      ExtentTest mainSuite = extent.startTest(suite.getName());
      for (ISuiteResult suiteResult : result.values()) {
        ITestContext context = suiteResult.getTestContext();
        ExtentTest extendSuite = extent.startTest(context.getName());
        buildTestNodes(context.getPassedTests(), LogStatus.PASS, extendSuite);
        buildTestNodes(context.getFailedTests(), LogStatus.FAIL, extendSuite);
        buildTestNodes(context.getSkippedTests(), LogStatus.SKIP, extendSuite);
        mainSuite.appendChild(extendSuite);
        extent.endTest(extendSuite);
      }
      extent.endTest(mainSuite);
    }
    for (String s : Reporter.getOutput()) {
      extent.setTestRunnerOutput(s);
    }
    Map<String, String> addDetails = new HashMap<String, String>();
    addDetails.put("brower", "firefox");
    addDetails.put("selenium version", "2.40");
    extent.addSystemInfo(addDetails);
    extent.flush();
    extent.close();
  }

  private void buildTestNodes(final IResultMap tests, final LogStatus status,
      final ExtentTest extendSuite) {
    ExtentTest test;

    if (tests.size() > 0) {
      for (ITestResult result : tests.getAllResults()) {
        test = extent.startTest(result.getMethod().getMethodName());

        test.setStartedTime(getTime(result.getStartMillis()));
        test.setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups())
          test.assignCategory(group);
        if (result.getThrowable() != null) {
          test.log(status, result.getThrowable());
        } else {
          test.log(status, "Test " + status.toString().toLowerCase() + "ed");
        }
        System.out.println(
            "the screen shot file name is " + (String) result.getAttribute("screenShotFileName"));
        String screenCastfileName = (String) result.getAttribute("screenCastName");
        System.out.println("the screen casting file name is " + screenCastfileName);
        if (!(result.getAttribute("screenShotFileName") == null)) {
          test.log(LogStatus.FAIL, "Testcase Failed", test.addBase64ScreenShot(
              DataUtils.getScreenShotFullPath((String) result.getAttribute("screenShotFileName"))));
          test.addScreenCapture((String) result.getAttribute("screenShotFileName"));
        }
        test.log(LogStatus.FAIL, "Screen Casting ",
            test.addScreencast(getFileName(screenCastfileName)));
        extendSuite.appendChild(test);
        extent.endTest(test);
      }
    }
  }

  private String getFileName(final String screenCastfileName) {
    File folder = new File(screenCastfileName);
    File files[] = folder.listFiles();
    Arrays.sort(files, (a, b) -> Long.compare(a.lastModified(), b.lastModified()));
    System.out.println(files[files.length - 1].getName());
    log.debug("screen cast full name " + screenCastfileName + files[0].getName());
    return screenCastfileName + "\\" + files[0].getName();
  }

  private Date getTime(final long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();
  }
}
