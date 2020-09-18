package com.scott.web.tests.pages;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.with;

import com.scott.web.datarepublic.elements.widget.TabComp;
import com.scott.web.datarepublic.pageobject.WebPage;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatePage extends WebPage {

  @FindBy(className = "postage-location__tab")
  public TabComp locationTab;

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
