package com.scott.web.base.elements.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * wraps a web element interface with extra functionality. Anything added here will be added to all
 * descendants.
 */
@ImplementedBy(ElementImpl.class)
public interface Element extends WebElement, WrapsElement, Locatable {
  /**
   * Returns true when the inner element is ready to be used.
   *
   * @return boolean true for an initialized WebElement, or false if we were somehow passed a null
   *     WebElement.
   */
  boolean elementWired();

  /**
   * gets WebDriver that is used to find elements
   *
   * @return WebDriver
   */
  WebDriver getWrappedDriver();

  /**
   * finds the locator of the element and re-initialises the element
   *
   * @return Element
   */
  Element refreshElement();

  /** Clicks on element using javascript */
  void clickUsingJs();

  /**
   * gets list of By locators used to identify this element. These locators can be used for wait
   * conditions e.g. (new
   * WebDriverWait(getDriver(),timeout)).until(ExpectedConditions.elementToBeClickable(new
   * ByChained(this.getBy())));
   *
   * @return Array of By
   */
  By[] getBy();
}
