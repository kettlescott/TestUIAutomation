package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.Element;
import com.scott.web.base.elements.base.ImplementedBy;

/** Text field functionality. */
@ImplementedBy(TextInputImpl.class)
public interface TextInput extends Element {
  /** @param text The text to type into the element. */
  void set(String text);

  /**
   * types text in to the element using javascript
   *
   * @param text to be typed into the element
   */
  void setUsingJs(String text);
}
