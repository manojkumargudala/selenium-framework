package com.demoqa.listeners;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
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
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.demoqa.utils.BaseFrameWorkInitializer;
import com.demoqa.utils.FilePathUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentReporterNG implements IReporter {
  private ExtentReports extent;
  Logger logger = Logger.getLogger(ExtentReporterNG.class);

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
        buildTestNodes(context.getExcludedMethods(), LogStatus.SKIP, extendSuite);
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

  private void buildTestNodes(final Collection<ITestNGMethod> excludedMethods, final LogStatus skip,
      final ExtentTest extendSuite) {
    for (ITestNGMethod itestMethod : excludedMethods) {
      ExtentTest test = extent.startTest(itestMethod.getMethodName());
      extendSuite.appendChild(test);
      test.log(skip, "it is being ignored");
      extent.endTest(test);
    }
  }

  private void buildTestNodes(final IResultMap tests, final LogStatus status,
      final ExtentTest extendSuite) {
    if (tests.size() > 0) {
      for (ITestResult result : tests.getAllResults()) {
        generateExtentTest(status, extendSuite, result);
      }
    }
  }

  private void generateExtentTest(final LogStatus status, final ExtentTest extendSuite,
      final ITestResult result) {
    ExtentTest test = extent.startTest(result.getMethod().getMethodName());
    test.setStartedTime(getTime(result.getStartMillis()));
    test.setEndedTime(getTime(result.getEndMillis()));
    for (String str : Reporter.getOutput(result)) {
      logger.debug(str);
      test.log(LogStatus.INFO, str);
    }
    for (String group : result.getMethod().getGroups())
      test.assignCategory(group);
    if (result.getThrowable() != null) {
      test.log(status, result.getThrowable());
    } else {
      test.log(status, "Test " + status.toString().toLowerCase() + "ed");
    }
    String screenShotFileName = (String) result.getAttribute("screenShotFileName");
    logger.debug("the screen shot file name is " + screenShotFileName);
    String screenCastfileName = (String) result.getAttribute("screenCastName");
    logger.debug("the screen casting file name is " + screenCastfileName);
    addScreenShot(test, screenShotFileName);
    addScreenCastToReport(test, screenCastfileName);
    extendSuite.appendChild(test);
    extent.endTest(test);
  }

  private void addScreenShot(ExtentTest test, String screenShotFileName) {
    if (screenShotFileName != null) {
      test.log(LogStatus.INFO, "Testcase Failed",
          test.addBase64ScreenShot(FilePathUtils.getScreenShotFullPath(screenShotFileName)));
    }
  }

  private void addScreenCastToReport(ExtentTest test, String screenCastfileName) {
    if (screenCastfileName != null && BaseFrameWorkInitializer.getInstance().isRunInDebugMode()) {
      test.log(LogStatus.INFO, "Screen Casting of the test case is",
          test.addScreencast(getFileName(screenCastfileName)));
    }
  }

  private String getFileName(final String screenCastfileName) {
    File folder = new File(screenCastfileName);
    File files[] = folder.listFiles();
    Arrays.sort(files, (a, b) -> Long.compare(a.lastModified(), b.lastModified()));
    String screnCaseFullPath = screenCastfileName + "\\" + files[0].getName();
    logger.debug("screen cast full name " + screenCastfileName);
    return screnCaseFullPath;
  }

  private Date getTime(final long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();
  }
}
