package com.scott.web.tests.tests;

import static org.awaitility.Awaitility.await;

import com.scott.web.datarepublic.test.AutoTest;
import com.scott.web.datarepublic.utils.AutoConfig;
import com.scott.web.tests.pages.CalculatePage;
import com.scott.web.tests.pages.DomesticPage;
import com.scott.web.tests.pages.ParcelServicesPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NegativePathTest extends AutoTest {

  CalculatePage calculatePage;

  @BeforeMethod
  public void beforeMethod() {
    given("Navigate to Aus URL");
    getDriver().get(AutoConfig.instance().getConfig("URL"));
    calculatePage = pageObjectVisitor.gotoPage(CalculatePage.class);
    calculatePage.maximizeWindow();
  }

  @Test(testName = "testInvalidAddress", description = "Invalid data for location")
  public void testInvalidAddress() {

    when("click on Australia domestic tab");
    calculatePage.locationTab.switchByName("Australia");
    DomesticPage domesticPage = pageObjectVisitor.gotoPage(DomesticPage.class);

    then("set invalid values for from, to");
    domesticPage.from.set("XXXXXXXXX");
    domesticPage.to.set("YYYYYYYYYY");

    and("click on go button");
    domesticPage.go.click();

    then("we verify error message");
    Assert.assertEquals(
        domesticPage.getErrorMesage(),
        "We don’t recognise that location. Please check that you’ve entered it correctly.",
        "verify error message");
  }

  @Test(
      testName = "testInvalidBox_Size_Weight_Length_Height_width",
      description = "invalid data for length, height, weight, width")
  public void testInvalidBox_Size_Weight_Length_Height_width() {

    when("click on Australia domestic tab");
    calculatePage.locationTab.switchByName("Australia");
    DomesticPage domesticPage = pageObjectVisitor.gotoPage(DomesticPage.class);

    then("set values for from, to");
    domesticPage.from.set("EASTWOOD NSW 2122");
    domesticPage.to.set("NORTH SYDNEY NSW 2055");

    and("click on go button");
    domesticPage.go.click();
    ParcelServicesPage parcelServicesPage = pageObjectVisitor.gotoPage(ParcelServicesPage.class);

    and("Set invalid values for length, height, weight, width");
    // (Optional) Bonus points verify size , weight, delivery date
    parcelServicesPage.entersizeAndweight.click();
    parcelServicesPage.length.set("0");
    parcelServicesPage.width.set("0");
    parcelServicesPage.height.set("10000");
    parcelServicesPage.weight.set("1000000");
    parcelServicesPage.setSizeAndWeight.click();

    then("we verify error message count");
    await().until(() -> parcelServicesPage.errors.size() >= 4);

    Set<String> messages =
        parcelServicesPage.errors.stream().map(e -> e.getText().trim()).collect(Collectors.toSet());

    then("we verify error message content");

    // HashSet O(1) string compare
    Assert.assertTrue(messages.contains("Minimum value is 1cm"), "verify error message");
    Assert.assertTrue(messages.contains("Max. 105cm"), "verify error message");
    Assert.assertTrue(messages.contains("Max. 22kg"), "verify error message");
  }

  @Test(
      testName = "test_non_numeric_characters_Weight_Length_Height_width",
      description = "Non numeric value")
  public void test_non_numeric_characters_Weight_Length_Height_width() {

    when("click on Australia domestic tab");
    calculatePage.locationTab.switchByName("Australia");
    DomesticPage domesticPage = pageObjectVisitor.gotoPage(DomesticPage.class);

    then("set values for from, to");
    domesticPage.from.set("EASTWOOD NSW 2122");
    domesticPage.to.set("NORTH SYDNEY NSW 2055");

    and("click on go button");
    domesticPage.go.click();
    ParcelServicesPage parcelServicesPage = pageObjectVisitor.gotoPage(ParcelServicesPage.class);

    and("Set no numeric values for length");
    // (Optional) Bonus points verify size , weight, delivery date
    parcelServicesPage.entersizeAndweight.click();
    parcelServicesPage.length.set("X");
    parcelServicesPage.setSizeAndWeight.click();

    then("we verify error message count");
    await().until(() -> parcelServicesPage.errors.size() >= 1);
  }

  @Test(testName = "test_empty_input", description = "no input")
  public void test_empty_input() {

    when("click on Australia domestic tab");
    calculatePage.locationTab.switchByName("Australia");
    DomesticPage domesticPage = pageObjectVisitor.gotoPage(DomesticPage.class);

    then("set values for from, to");
    domesticPage.from.set("EASTWOOD NSW 2122");
    domesticPage.to.set("NORTH SYDNEY NSW 2055");

    and("click on go button");
    domesticPage.go.click();
    ParcelServicesPage parcelServicesPage = pageObjectVisitor.gotoPage(ParcelServicesPage.class);

    and("Set no numeric values for length");
    // (Optional) Bonus points verify size , weight, delivery date
    parcelServicesPage.entersizeAndweight.click();
    parcelServicesPage.setSizeAndWeight.click();

    then("we verify error message count");
    await().until(() -> parcelServicesPage.errors.size() >= 1);

    Set<String> messages =
        parcelServicesPage.errors.stream().map(e -> e.getText().trim()).collect(Collectors.toSet());

    then("we verify error message content");

    // HashSet O(1) string compare
    Assert.assertTrue(messages.contains("Enter width"), "verify error message");
    Assert.assertTrue(messages.contains("Enter length"), "verify error message");
    Assert.assertTrue(messages.contains("Enter height"), "verify error message");
    Assert.assertTrue(messages.contains("Enter weight"), "verify error message");
  }

  @Test(testName = "testInvalidDeliverDate", description = "set an invalid deliver date")
  public void testInvalidDeliverDate() {

    when("click on Australia domestic tab");
    calculatePage.locationTab.switchByName("Australia");
    DomesticPage domesticPage = pageObjectVisitor.gotoPage(DomesticPage.class);

    then("set values for from, to");
    domesticPage.from.set("EASTWOOD NSW 2122");
    domesticPage.to.set("NORTH SYDNEY NSW 2055");

    and("click on go button");
    domesticPage.go.click();
    ParcelServicesPage parcelServicesPage = pageObjectVisitor.gotoPage(ParcelServicesPage.class);

    parcelServicesPage.enterADate.click();
    and("set a date which is less than the current date");
    String sendOnDate =
        LocalDate.now().plusDays(-2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    parcelServicesPage.date.set(sendOnDate);
    parcelServicesPage.setDate.click();

    // bug the delivery date should be allowed  to less than the current date
    then(
        "we verify delivery date should not be allowed  to less than the current date and an exception should be thrown");
    Assert.assertTrue(
        parcelServicesPage.errors.size() >= 1,
        "delivery date should not be allowed  to less than the current date and an exception should be thrown");
  }

  @Test(testName = "testEmptyDate", description = "no delivery date")
  public void testEmptyDate() {

    when("click on Australia domestic tab");
    calculatePage.locationTab.switchByName("Australia");
    DomesticPage domesticPage = pageObjectVisitor.gotoPage(DomesticPage.class);

    then("set values for from, to");
    domesticPage.from.set("EASTWOOD NSW 2122");
    domesticPage.to.set("NORTH SYDNEY NSW 2055");

    and("click on go button");
    domesticPage.go.click();
    ParcelServicesPage parcelServicesPage = pageObjectVisitor.gotoPage(ParcelServicesPage.class);

    parcelServicesPage.enterADate.click();
    // set a date which is less than the current date
    and("we do not set date value");
    parcelServicesPage.setDate.click();

    then("we verify there is a error message shown");

    await().until(() -> parcelServicesPage.date_errors.size() >= 1);

    List<String> messages =
        parcelServicesPage.date_errors.stream()
            .map(e -> e.getText().trim())
            .collect(Collectors.toList());

    then("we verify error message content");

    Assert.assertTrue(
        messages.get(0).trim().startsWith("Use date format dd/mm/yyyy"), "verify error message");
  }
}
