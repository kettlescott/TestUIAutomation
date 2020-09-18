package com.scott.web.datarepublic.utils;

import com.scott.web.base.utils.ConfigManager;

public class AutoConfig {

  private ConfigManager manager = ConfigManager.instance();

  private static AutoConfig instance = null;

  private AutoConfig() {}

  public static synchronized AutoConfig instance() {
    if (instance == null) {
      instance = new AutoConfig();
    }
    return instance;
  }

  public String getConfig(String configName) {
    return manager.getByKey(configName);
  }
}
