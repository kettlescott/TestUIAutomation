package com.scott.web.datarepublic.elements.widget;

import com.scott.web.base.elements.base.ImplementedBy;

@ImplementedBy(TabCompImpl.class)
public interface TabComp extends BaseElement {

  void switchById(int idx);

  void switchByName(String Name);
}
