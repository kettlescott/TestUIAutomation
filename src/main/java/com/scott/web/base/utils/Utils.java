package com.scott.web.base.utils;

import static io.github.bonigarcia.wdm.DriverManagerType.*;

import io.github.bonigarcia.wdm.DriverManagerType;

public class Utils {
  private static ConfigManager configManager = ConfigManager.instance();

  private Utils() {
    throw new IllegalStateException("Utils is a utility class");
  }

  public static DriverManagerType getDriverManagerType() {
    String driver = configManager.getByKey("DRIVER").toUpperCase();
    switch (driver) {
      case "CHROME":
      case "GOOGLE CHROME":
        return CHROME;
      default:
        throw new RuntimeException("cannot find driver type:" + driver + " expected: {CHROME}");
    }
  }
}
