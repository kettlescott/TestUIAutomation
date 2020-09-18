package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.Element;
import com.scott.web.base.elements.base.ImplementedBy;

/** Html form label. */
@ImplementedBy(LabelImpl.class)
public interface Label extends Element {
  /**
   * Gets the for attribute on the label.
   *
   * @return string containing value of the for attribute, null if empty.
   */
  String getFor();
}
