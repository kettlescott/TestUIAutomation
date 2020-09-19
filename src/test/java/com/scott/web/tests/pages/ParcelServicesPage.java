package com.scott.web.tests.pages;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;

import com.scott.web.base.elements.widget.Button;
import com.scott.web.base.elements.widget.Label;
import com.scott.web.base.elements.widget.Link;
import com.scott.web.base.elements.widget.TextInput;
import com.scott.web.datarepublic.elements.widget.ParcelInforComp;
import com.scott.web.datarepublic.pageobject.WebPage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class ParcelServicesPage extends WebPage {

  public ParcelServicesPage(WebDriver driver) {
    super(driver);
  }

  @FindAll(@FindBy(css = "div.postage-service"))
  public List<ParcelInforComp> parcelServices;

  @FindAll(@FindBy(css = "div.errorMessageBox"))
  public List<Label> errors;

  @FindAll(@FindBy(css = "div.delivery-times__input-field-container__error-message-container"))
  public List<ParcelInforComp> date_errors;

  @FindBy(name = "lengthInput")
  public TextInput length;

  @FindBy(name = "widthInput")
  public TextInput width;

  @FindBy(name = "heightInput")
  public TextInput height;

  @FindBy(name = "weightInput")
  public TextInput weight;

  @FindBy(id = "submit-set-dimensions")
  public Button setSizeAndWeight;

  @FindBy(className = "size-weight__enter-weight-link")
  public Link entersizeAndweight;

  @FindBy(className = "delivery-times__enter-time-link")
  public Link enterADate;

  @FindBy(className = "delivery-times__input-field")
  public TextInput date;

  @FindBy(id = "set-date")
  public Button setDate;

  @Override
  public boolean exists() {
    try {
      with()
          .pollInterval(2, TimeUnit.SECONDS)
          .await()
          .atMost(60, SECONDS)
          .until(
              () ->
                  driver.findElements(By.cssSelector("div.postage-service")).stream()
                      .anyMatch(WebElement::isDisplayed));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
