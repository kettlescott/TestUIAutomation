package com.scott.web.base.driver;

import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserOptions implements Options {

  private ChromeOptions chromeOptions = new ChromeOptions();

  @Override
  public ChromeOptions getChromeOptions() {
    chromeOptions.addArguments("-disable-popup-blocking");
    chromeOptions.addArguments("--disable-notifications");
    return chromeOptions;
  }
}
