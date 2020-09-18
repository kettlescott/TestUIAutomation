package com.scott.web.datarepublic.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

  public static void checkExists(boolean conditions, String error) {
    if (!conditions) error(error);
  }

  public static void error(String error) {
    throw new RuntimeException("Error : " + error);
  }

  public static String createFolder(String path) {
    File dir = new File(path);
    dir.mkdir();
    return path;
  }

  public static String getCurrentTimestamp(String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    String time = dateFormat.format(new Date());
    return replace(time);
  }

  private static String replace(String s) {
    char[] chs = s.toCharArray();
    for (int i = 0; i < chs.length; ++i) {
      if (Character.isLetterOrDigit(chs[i])) continue;
      chs[i] = '_';
    }
    return new String(chs);
  }
}
