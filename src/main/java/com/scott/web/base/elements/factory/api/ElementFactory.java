package com.scott.web.base.elements.factory.api;

import com.scott.web.base.elements.factory.internal.ElementDecorator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public class ElementFactory {

  public static <T> T initElements(
      WebDriver driver, Class<T> pageClassToProxy, Class[] types, Object[] objects) {
    T page = instantiatePage(pageClassToProxy, types, objects);
    return initElements(driver, page);
  }

  /**
   * @param searchContext A search context that will be used to look up the elements
   * @param page The object with WebElement and List<WebElement> fields that should be proxied.
   * @return the initialized page-object.
   */
  public static <T> T initElements(SearchContext searchContext, T page) {
    initElements(new ElementDecorator(new DefaultElementLocatorFactory(searchContext)), page);
    return page;
  }

  public static void initElements(FieldDecorator decorator, Object page) {
    PageFactory.initElements(decorator, page);
  }

  private static <T> T instantiatePage(Class<T> pageClassToProxy, Class[] types, Object[] objs) {
    try {
      try {
        Constructor<T> constructor = pageClassToProxy.getConstructor(types);
        return constructor.newInstance(objs);
      } catch (NoSuchMethodException e) {
        return pageClassToProxy.newInstance();
      }
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}
