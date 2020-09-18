package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.Element;
import com.scott.web.base.elements.base.ImplementedBy;

@ImplementedBy(LinkImpl.class)
public interface Link extends Element {
  String getLinkReference();
}
