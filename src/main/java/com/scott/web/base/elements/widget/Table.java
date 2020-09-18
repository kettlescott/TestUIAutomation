package com.scott.web.base.elements.widget;

import com.scott.web.base.elements.base.Element;
import com.scott.web.base.elements.base.ImplementedBy;
import org.openqa.selenium.WebElement;

/** Table functionality. */
@ImplementedBy(TableImpl.class)
public interface Table extends Element {

  /**
   * Gets the number of rows in the table
   *
   * @return int equal to the number of rows in the table
   */
  int getRowCount();

  /**
   * Gets the number of columns in the table
   *
   * @return int equal to the number of rows in the table
   */
  int getColumnCount();

  /**
   * Gets the WebElement of the cell atPage the specified index
   *
   * @param rowIdx The zero based index of the row
   * @param colIdx The zero based index of the column
   * @return the WebElement of the cell atPage the specified index
   */
  WebElement getCellAtIndex(int rowIdx, int colIdx);
}
