package com.scott.web.base.driver;

import org.openqa.selenium.WebDriver;

interface Browser {
  WebDriver getDriver(Options options);
}
