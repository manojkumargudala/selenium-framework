package com.demoqa.listeners;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentReporterNG implements IReporter {
  private ExtentReports extent;

  @Override
  public void generateReport(final List<XmlSuite> xmlSuites, final List<ISuite> suites,
      final String outputDirectory) {
    extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true,
        NetworkMode.OFFLINE);
    File extenrReportConfig = new File("src/main/resources/extentreports.xml");
    extent.loadConfig(extenrReportConfig);
    for (ISuite suite : suites) {
      Map<String, ISuiteResult> result = suite.getResults();

      for (ISuiteResult r : result.values()) {
        ITestContext context = r.getTestContext();

        buildTestNodes(context.getPassedTests(), LogStatus.PASS);
        buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
        buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
      }
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

  private void buildTestNodes(final IResultMap tests, final LogStatus status) {
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
        if (!(result.getAttribute("screenShotFileName") == null)) {
          test.addScreenCapture((String) result.getAttribute("screenShotFileName"));
        }
        extent.endTest(test);
      }
    }
  }

  private Date getTime(final long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();
  }
}
