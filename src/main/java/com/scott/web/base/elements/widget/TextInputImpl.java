package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.ElementImpl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/** TextInput wrapper. */
public class TextInputImpl extends ElementImpl implements TextInput {
  /**
   * Creates a Element for a given WebElement.
   *
   * @param element element to wrap up
   */
  public TextInputImpl(WebElement element) {
    super(element);
  }

  @Override
  public void clear() {
    getWrappedElement().clear();
  }

  @Override
  public void set(String text) {
    WebElement element = getWrappedElement();
    element.clear();
    element.sendKeys(text);
  }

  /**
   * types text in to the element using javascript
   *
   * @param text to be typed into the element
   */
  @Override
  public void setUsingJs(String text) {
    JavascriptExecutor jse = (JavascriptExecutor) getWrappedDriver();
    jse.executeScript("arguments[0].value='" + text + "';", this.getWrappedElement());
  }

  /**
   * Gets the value of an input field.
   *
   * @return String with the value of the field.
   */
  @Override
  public String getText() {
    return getWrappedElement().getAttribute("value");
  }

  @Override
  public TextInput refreshElement() {
    return (TextInput) super.refreshElement();
  }
}
