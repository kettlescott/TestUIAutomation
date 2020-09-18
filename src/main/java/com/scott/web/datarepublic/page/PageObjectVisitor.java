package com.scott.web.datarepublic.page;

import com.scott.web.base.page.PageVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class PageObjectVisitor {

  private PageVisitor visitor;

  private static final Logger logger = LogManager.getLogger();

  public PageObjectVisitor(WebDriver driver) {
    visitor = new PageVisitor(driver);
  }

  public <T> T gotoPage(Class<T> c) {
    T t = visitor.visit(c, new Class[] {WebDriver.class}, new Object[] {});
    logger.debug("Page " + c.getSimpleName() + " is created");
    return t;
  }

  public void clearCache() {
    visitor.clearCache();
  }
}
