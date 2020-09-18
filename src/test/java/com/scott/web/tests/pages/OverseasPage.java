package com.scott.web.tests.pages;

import com.scott.web.base.elements.widget.Button;
import com.scott.web.base.elements.widget.Select;
import com.scott.web.base.elements.widget.TextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class OverseasPage extends CalculatePage {

  @FindBy(id = "intFrom_value")
  public TextInput from;

  @FindBy(id = "intTo")
  public Select to;

  @FindBy(id = "intToPostcode")
  public TextInput postCode;

  @FindBy(id = "submit-international")
  public Button go;

  public OverseasPage(WebDriver driver) {
    super(driver);
  }
}
