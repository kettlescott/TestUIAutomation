package com.scott.web.datarepublic.elements.widget;

import com.scott.web.base.elements.base.Element;
import com.scott.web.base.elements.base.ImplementedBy;

@ImplementedBy(BaseElementImpl.class)
public interface BaseElement extends Element {
  boolean isReady();

  boolean exists();

  BaseElement refreshElement();
}
