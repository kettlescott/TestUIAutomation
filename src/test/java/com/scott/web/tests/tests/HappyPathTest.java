package com.scott.web.tests.tests;

import com.scott.web.datarepublic.elements.widget.FeaturesOptions;
import com.scott.web.datarepublic.elements.widget.ParcelInforComp;
import com.scott.web.datarepublic.test.AutoTest;
import com.scott.web.datarepublic.utils.AutoConfig;
import com.scott.web.tests.pages.CalculatePage;
import com.scott.web.tests.pages.DomesticPage;
import com.scott.web.tests.pages.ParcelServicesPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HappyPathTest extends AutoTest {

  CalculatePage calculatePage;

  @BeforeMethod
  public void beforeMethod() {
    given("Navigate to Aus URL");
    getDriver().get(AutoConfig.instance().getConfig("URL"));
    calculatePage = pageObjectVisitor.gotoPage(CalculatePage.class);
    calculatePage.maximizeWindow();
  }

  /**
   * Enter a source and destination postcode by entering a postcode and selecting from the dropdown
   * list Validate the cost Verify that there are 2 options (express post / parcel post) listed down
   * (Optional) Bonus points: you can enter size weight delivery date
   */
  @Test(testName = "Calculation", description = "calculate cost for a parcel service")
  public void testCalculation() {

    when("click on Australia domestic tab");
    calculatePage.locationTab.switchByName("Australia");
    DomesticPage domesticPage = pageObjectVisitor.gotoPage(DomesticPage.class);

    then("set values for from, to");
    domesticPage.from.set("EASTWOOD NSW 2122");
    domesticPage.to.set("NORTH SYDNEY NSW 2055");

    and("click on go button");
    domesticPage.go.click();
    ParcelServicesPage parcelServicesPage = pageObjectVisitor.gotoPage(ParcelServicesPage.class);
    HashMap<String, ParcelInforComp> options = toOptions(parcelServicesPage.parcelServices);

    and("Verify that there are 2 options (express post /Courier post) listed down;");
    // requirement 1 ,Verify that there are 2 options (express post /Courier post) listed down;
    Assert.assertTrue(options.containsKey("Express Post"), "could not find Express Post");
    Assert.assertTrue(options.containsKey("Parcel Post"), "could not find Parcel Post");

    // Validate the cost
    and("Verify cost");
    Assert.assertEquals(options.get("Express Post").getPrice(), "$11.95");

    Assert.assertEquals(options.get("Parcel Post").getPrice(), "$8.95");

    and("Set Optional) Bonus points verify size , weight, delivery date");
    // (Optional) Bonus points verify size , weight, delivery date
    parcelServicesPage.entersizeAndweight.click();
    parcelServicesPage.length.set("10");
    parcelServicesPage.width.set("10");
    parcelServicesPage.height.set("10");
    parcelServicesPage.weight.set("10");
    parcelServicesPage.setSizeAndWeight.click();

    parcelServicesPage.enterADate.click();
    String sendOnDate =
        LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    parcelServicesPage.date.set(sendOnDate);
    parcelServicesPage.setDate.click();

    // verify cost with new Configuration
    options = toOptions(parcelServicesPage.parcelServices);

    // Validate the cost
    Assert.assertEquals(options.get("Express Post").getPrice(), "$26.00");
    Assert.assertEquals(options.get("Parcel Post").getPrice(), "$19.00");

    // expanding Features & options
    FeaturesOptions featuresOptions = options.get("Express Post").expandFeaturesOptions();

    HashMap<String, String> cost_result = featuresOptions.getResult();

    and("Verify cost again");
    // verify cost with new Configuration in the Features & options
    Assert.assertEquals(cost_result.get("cost"), "$26.00");
    Assert.assertEquals(cost_result.get("size"), "10 x 10 x 10cm");
    Assert.assertEquals(cost_result.get("weight"), "10kg");
  }

  private HashMap<String, ParcelInforComp> toOptions(List<ParcelInforComp> parcelServices) {
    HashMap<String, ParcelInforComp> options = new HashMap<>();
    parcelServices.forEach(
        e -> {
          options.put(e.getServicesName(), e);
        });
    return options;
  }
}
