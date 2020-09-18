package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.ElementImpl;
import org.openqa.selenium.WebElement;

public class ImageImpl extends ElementImpl implements Image {
  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  public ImageImpl(WebElement element) {
    super(element);
  }

  @Override
  public String getSource() {
    return getAttribute("src");
  }

  @Override
  public String getAltText() {
    return getAttribute("alt");
  }

  @Override
  public Image refreshElement() {
    return (Image) super.refreshElement();
  }
}
