package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.Element;
import com.scott.web.base.elements.base.ImplementedBy;

@ImplementedBy(ImageImpl.class)
public interface Image extends Element {
  String getSource();

  String getAltText();
}
