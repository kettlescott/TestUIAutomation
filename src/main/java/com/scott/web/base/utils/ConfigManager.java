package com.scott.web.base.utils;

import com.scott.web.datarepublic.utils.Helper;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ConfigManager {

  private static ConfigManager instance;

  private static final Logger logger = LogManager.getLogger();

  private HashMap<String, String> configValues;

  private static final String CONFIG = "configFilePath";

  private static final String FILE = "configuration.xml";

  private String errorMsg = "Duplicate Keys found in configuration:\n";

  public static ConfigManager instance() {
    if (instance == null) instance = new ConfigManager();
    return instance;
  }

  private ConfigManager() {
    configValues = new HashMap<>();
    init();
  }

  private void init() {
    try {
      String filePath = System.getProperty(CONFIG) == null ? FILE : System.getProperty(CONFIG);
      Helper.checkExists(new File(filePath).exists(), "cannot find config file {" + filePath + "}");
      // load user configuration
      loadUserConfig(filePath);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public String getByKey(String key) {
    return configValues.getOrDefault(key, "");
  }

  private void loadUserConfig(String filePath) {
    SAXBuilder builder = new SAXBuilder();
    try {
      Document xml = builder.build(new File(filePath));
      load(xml, configValues);
    } catch (JDOMException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void load(Document xmlFile, HashMap<String, String> handle) {
    Element rootNode = xmlFile.getRootElement();
    List<Element> list = rootNode.getChildren("appSettings");
    if (list.size() == 0) return;
    for (Element e : list.get(0).getChildren("add")) {
      String key = e.getAttributeValue("key");
      String value = e.getAttributeValue("value");
      if (handle.containsKey(key)) {
        errorMsg += "{" + key + "}";
        logger.error(errorMsg);
        throw new RuntimeException(errorMsg);
      }
      handle.put(key, value);
    }
  }
}
