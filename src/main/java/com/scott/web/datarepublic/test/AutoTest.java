package com.scott.web.datarepublic.test;

import com.scott.web.base.driver.BrowserOptions;
import com.scott.web.base.driver.DriverManager;
import com.scott.web.base.test.Test;
import com.scott.web.datarepublic.page.PageObjectVisitor;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AutoTest extends Test {

  protected PageObjectVisitor pageObjectVisitor;

  @BeforeMethod
  public void before(Method method) {
    setTestName(
        method.getAnnotation(org.testng.annotations.Test.class).testName().isEmpty()
            ? method.getName()
            : method.getAnnotation(org.testng.annotations.Test.class).testName());

    setTestDescription(
        method.getAnnotation(org.testng.annotations.Test.class).description().isEmpty()
            ? ""
            : method.getAnnotation(org.testng.annotations.Test.class).description());

    String reportTitle =
        getTestDescription().isEmpty()
            ? getTestName()
            : getTestName() + " - " + getTestDescription();

    setExtentTest(getReport().createTest(reportTitle));
    setDriver(DriverManager.getDriver(new BrowserOptions()));
    pageObjectVisitor = new PageObjectVisitor(getDriver());
  }

  @Override
  @AfterMethod
  public void cleanup(ITestResult result) {
    super.cleanup(result);
    if (pageObjectVisitor != null) {
      pageObjectVisitor.clearCache();
    }
  }
}
