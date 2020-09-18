package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.ElementImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class LinkImpl extends ElementImpl implements Link {
  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  private static final Logger logger = LogManager.getLogger();

  public LinkImpl(WebElement element) {
    super(element);
  }

  public String getLinkReference() {
    return getAttribute("href");
  }

  @Override
  public void click() {
    super.click();
  }

  @Override
  public Link refreshElement() {
    return (Link) super.refreshElement();
  }
}
