package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.ElementImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class ButtonImpl extends ElementImpl implements Button {
  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  private static final Logger logger = LogManager.getLogger();

  public ButtonImpl(WebElement element) {
    super(element);
  }

  // this monitors click on a button
  @Override
  public void click() {
    String property =
        this.getAttribute("class").length() != 0
            ? this.getAttribute("class")
            : this.getAttribute("id");
    super.click();
  }

  @Override
  public Button refreshElement() {
    return (Button) super.refreshElement();
  }
}
