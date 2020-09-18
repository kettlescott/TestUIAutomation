package com.scott.web.datarepublic.elements.widget;

import com.scott.web.base.elements.base.ElementImpl;
import org.openqa.selenium.WebElement;

public class BaseElementImpl extends ElementImpl implements BaseElement {

  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  public BaseElementImpl(WebElement element) {
    super(element);
  }

  @Override
  public boolean isReady() {
    return elementWired();
  }

  @Override
  public boolean exists() {
    return elementWired();
  }

  @Override
  public BaseElement refreshElement() {
    return (BaseElement) super.refreshElement();
  }
}
