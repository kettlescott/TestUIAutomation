package com.scott.web.datarepublic.elements.widget;

import static java.util.stream.Collectors.joining;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TabCompImpl extends BaseElementImpl implements TabComp {

  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  private static final String INTERNATIONAL_TAB =
      "postage-location__tab__container__name__international";

  private static final String DOMESTIC_TAB = "postage-location__tab__container__name__domestic";

  private HashMap<Integer, String> mapping;

  private HashMap<String, Integer> naming;

  public TabCompImpl(WebElement element) {
    super(element);
    init();
  }

  private void init() {
    mapping = new HashMap<>();
    naming = new HashMap<>();
    mapping.put(1, DOMESTIC_TAB);
    mapping.put(2, INTERNATIONAL_TAB);
    naming.put("Australia", 1);
    naming.put("Overseas", 2);
  }

  @Override
  public void switchById(int idx) {
    checkRange(idx);
    switchTo(idx);
  }

  @Override
  public void switchByName(String name) {
    checkName(name);
    switchTo(naming.get(name));
  }

  private void switchTo(int idx) {
    findElement(By.cssSelector("div." + mapping.get(idx))).click();
  }

  private void checkRange(int idx) {
    if (!mapping.containsKey(idx)) {
      throw new RuntimeException(printError(idx));
    }
  }

  private void checkName(String name) {
    if (!naming.containsKey(name)) {
      throw new RuntimeException(printError(name));
    }
  }

  private String printError(int idx) {
    String dump = "[" + mapping.keySet().stream().map(Object::toString).collect(joining(",")) + "]";
    return "could not find idx : " + idx + " , the valid idx should be " + dump;
  }

  private String printError(String name) {
    String dump = "[" + naming.keySet().stream().map(Object::toString).collect(joining(",")) + "]";
    return "could not find name : " + name + " , the valid name should be " + dump;
  }
}
