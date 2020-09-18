package com.scott.web.datarepublic.pageobject;

import org.openqa.selenium.WebDriver;

public abstract class WebPage extends Page {

  public WebPage(WebDriver driver) {
    super(driver);
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public void maximizeWindow() {
    driver.manage().window().maximize();
  }
}
