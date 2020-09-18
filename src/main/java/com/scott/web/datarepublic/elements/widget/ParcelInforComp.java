package com.scott.web.datarepublic.elements.widget;

import com.scott.web.base.elements.base.ImplementedBy;

@ImplementedBy(ParcelInforCompImpl.class)
public interface ParcelInforComp extends BaseElement {

  String getServicesName();

  String getMaxWeight();

  String getDeliveryEstimate();

  String getPrice();

  FeaturesOptions expandFeaturesOptions();
}
