package com.scott.web.datarepublic.elements.widget;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FeaturesOptionsImpl extends BaseElementImpl implements FeaturesOptions {

  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  private static final String DATE_CSS = "span.postage-service__section__delivery-time-label";

  private static final String ROOT = "div.result__container";
  private static final String SIZE = "div.size";
  private static final String WEIGHT = "div.weight";
  private static final String COST = "div.price__wrapper";

  public FeaturesOptionsImpl(WebElement element) {
    super(element);
    with().pollInterval(2, TimeUnit.SECONDS).await().atMost(60, SECONDS).until(this::exists);
  }

  @Override
  public String getDeliveryDate() {
    return findElement(By.cssSelector(DATE_CSS)).getText();
  }

  @Override
  public HashMap<String, String> getResult() {
    HashMap<String, String> ret = new HashMap<>();
    ret.put("size", extract(findElement(By.cssSelector(ROOT + " " + SIZE)).getText()));
    ret.put("weight", extract(findElement(By.cssSelector(ROOT + " " + WEIGHT)).getText()));
    ret.put("cost", findElement(By.cssSelector(ROOT + " " + COST)).getText());
    return ret;
  }

  @Override
  public boolean exists() {
    return findElements(By.cssSelector(ROOT)).stream().anyMatch(WebElement::isDisplayed);
  }

  private String extract(String s) {
    if (s == null || s.length() == 0) return "";
    String[] val = s.split(":");
    if (val.length <= 1) return "";
    return val[1].trim();
  }
}
