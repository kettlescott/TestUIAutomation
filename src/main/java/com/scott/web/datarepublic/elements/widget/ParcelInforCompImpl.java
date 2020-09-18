package com.scott.web.datarepublic.elements.widget;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ParcelInforCompImpl extends BaseElementImpl implements ParcelInforComp {

  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  public ParcelInforCompImpl(WebElement element) {
    super(element);
  }

  private static final String LABEL = "span.postage-service__section__name-label";
  private static final String WEIGHT = "span.postage-service__section__weight";
  private static final String DELIVERY = "span.postage-service__section__deliverytime";
  private static final String COST = "span.postage-service__section__price";
  private static final String LINK = "div.postage-service__section";

  @Override
  public String getServicesName() {
    return findElement(By.cssSelector(LABEL)).getText();
  }

  @Override
  public String getMaxWeight() {
    return findElement(By.cssSelector(WEIGHT)).getText();
  }

  @Override
  public String getDeliveryEstimate() {
    return findElement(By.cssSelector(DELIVERY)).getText();
  }

  @Override
  public String getPrice() {
    return findElement(By.cssSelector(COST)).getText();
  }

  @Override
  public FeaturesOptions expandFeaturesOptions() {
    findElement(By.cssSelector(LINK)).click();
    return new FeaturesOptionsImpl(this);
  }

  // avoid stale element exception
  @Override
  public ParcelInforCompImpl refreshElement() {
    return (ParcelInforCompImpl) super.refreshElement();
  }
}
