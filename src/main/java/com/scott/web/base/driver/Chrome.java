package com.scott.web.base.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements Browser {
  @Override
  public WebDriver getDriver(Options options) {
    return new ChromeDriver(options.getChromeOptions());
  }
}
