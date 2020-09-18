package com.scott.web.base.driver;

import static io.github.bonigarcia.wdm.DriverManagerType.*;

import com.scott.web.base.utils.Utils;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.EnumMap;
import org.openqa.selenium.WebDriver;

public class DriverManager {

  private static EnumMap<DriverManagerType, Browser> browsers =
      new EnumMap<>(DriverManagerType.class);

  static {
    WebDriverManager manager = WebDriverManager.getInstance(Utils.getDriverManagerType());
    manager.setup();
    browsers.put(CHROME, new Chrome());
  }

  private DriverManager() {
    throw new IllegalStateException("DriverManager is a utility class");
  }

  public static WebDriver getDriver(Options options) {
    return browsers.get(Utils.getDriverManagerType()).getDriver(options);
  }
}
