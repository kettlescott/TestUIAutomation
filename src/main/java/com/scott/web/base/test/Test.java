package com.scott.web.base.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.scott.web.base.utils.ConfigManager;
import com.scott.web.datarepublic.utils.Helper;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

public abstract class Test {
  private WebDriver driver;
  private String testName;
  private String testDescription;
  private static volatile ExtentReports report;
  private ExtentTest extentTest;
  private static ConfigManager configManager = ConfigManager.instance();
  private static final String DATE_FORMAT = "dd/MM/yyyy hh/mm/ss";
  private static String root;

  static {
    root =
        new File(configManager.getByKey("REPORT")).getAbsolutePath()
            + "/"
            + "report_"
            + Helper.getCurrentTimestamp(DATE_FORMAT);
    Helper.createFolder(root);
  }

  @BeforeSuite
  public void beforeSuite() {
    report = new ExtentReports();
    report.attachReporter(new ExtentHtmlReporter(root + "/" + "report.html"));
  }

  @AfterMethod
  public void cleanup(ITestResult result) {
    if (result.getStatus() == ITestResult.SUCCESS) {
      extentTest.pass(testName + " passed");
    } else {
      extentTest.fail(testName + " failed");
      extentTest.info(result.getThrowable());
      try {
        extentTest.addScreenCaptureFromPath(takeScreenshot());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }

  @AfterSuite
  public void cleanupSuite() {
    report.flush();
  }

  public WebDriver getDriver() {
    return driver;
  }

  public void setDriver(WebDriver driver) {
    this.driver = driver;
  }

  public void given(String given) {
    extentTest.info("<b>Given</b> " + given);
  }

  public void when(String when) {
    extentTest.info("<b>When</b> " + when);
  }

  public void and(String and) {
    extentTest.info("<b>And</b> " + and);
  }

  public void then(String then) {
    extentTest.info("<b>Then</b> " + then);
  }

  private String takeScreenshot() throws IOException {
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File file =
        new File(root + "/" + testName + "_" + Helper.getCurrentTimestamp(DATE_FORMAT) + ".png");
    FileUtils.copyFile(scrFile, file);
    return file.getAbsolutePath();
  }

  public static ExtentReports getReport() {
    return report;
  }

  public void setExtentTest(ExtentTest extentTest) {
    this.extentTest = extentTest;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public String getTestDescription() {
    return testDescription;
  }

  public void setTestDescription(String testDescription) {
    this.testDescription = testDescription;
  }
}
