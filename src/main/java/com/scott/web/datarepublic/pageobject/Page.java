package com.scott.web.datarepublic.pageobject;

import org.openqa.selenium.WebDriver;

public abstract class Page {

  protected WebDriver driver;

  public Page(WebDriver driver) {
    this.driver = driver;
  }

  public abstract boolean exists();
}
