package com.scott.web.tests.pages;

import com.scott.web.base.elements.widget.Button;
import com.scott.web.base.elements.widget.TextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class DomesticPage extends CalculatePage {

  @FindBy(id = "domFrom_value")
  public TextInput from;

  @FindBy(id = "domTo_value")
  public TextInput to;

  @FindBy(id = "submit-domestic")
  public Button go;

  public DomesticPage(WebDriver driver) {
    super(driver);
  }
}
