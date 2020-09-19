package com.scott.web.tests.pages;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;

import com.scott.web.base.elements.widget.Label;
import com.scott.web.datarepublic.elements.widget.TabComp;
import com.scott.web.datarepublic.pageobject.WebPage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class CalculatePage extends WebPage {

  @FindBy(className = "postage-location__tab")
  public TabComp locationTab;

  @FindAll(@FindBy(id = "domFrom-address-not-verified-error"))
  public List<Label> errors;

  public String getErrorMesage() {
    try {
      with()
          .pollInterval(2, TimeUnit.SECONDS)
          .await()
          .atMost(60, SECONDS)
          .until(() -> errors.size() > 0);
    } catch (Exception e) {
      throw new RuntimeException("error message does not exist");
    }
    return errors.get(0).getText();
  }

  public CalculatePage(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean exists() {
    try {
      with()
          .pollInterval(2, TimeUnit.SECONDS)
          .await()
          .atMost(60, SECONDS)
          .until(
              () ->
                  driver.findElements(By.cssSelector("div.postage-location__tab")).stream()
                      .anyMatch(WebElement::isDisplayed));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
