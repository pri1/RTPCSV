package com.coding.task.quantcast.validators;

import static com.coding.task.quantcast.constants.Constants.DATE_REGEX;

import com.coding.task.quantcast.constants.Constants;
import com.coding.task.quantcast.exception.TestClass.InvalidApplicationException;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvFileValidator {

  private static final Logger logger = LoggerFactory.getLogger(CsvFileValidator.class);

  public static void validateFileExtension(String fileName) throws Exception {
    if (!Pattern.compile(Constants.FILE_EXTENSION_PATTERN).matcher(fileName).matches()) {
      throw new InvalidApplicationException(Constants.CSV_FILE_EXTENSION_ERROR_ERROR_MESSAGE);
    }
  }

  public static String dateValidation(String date) throws Exception {

    String[] split = date.split("T");
    validateDate(split[0]);
    return split[0];
  }

  public static void validateDate(String date) throws Exception {
    Pattern pattern = Pattern.compile(DATE_REGEX);
    if (!pattern.matcher(date).matches()) {
      logger.error("Exception occurred while date is invalid:{} ", date);
      throw new Exception("invalid date");
    }
  }

  public static boolean validateCookieIdLength(String cookie) {
    return cookie.length() == 16 ? true : false;
  }

  public static boolean validateTimeStampLength(String timestamp) {
    return timestamp.length() == 25 ? true : false;
  }

  public static boolean isAlphaNumeric(String cookie) throws Exception {
    if (cookie.isEmpty() || cookie == null) {
      logger.error("Exception occurred while cookie is empty:{} ", cookie);
      throw new Exception("ROW_MISSING_ERROR_CODE ROW_MISSING_ERROR_ERROR_MESSAGE");
    }
    Pattern p = Pattern.compile(Constants.REGEX);
    if (cookie == null || p.matcher(cookie).matches() == false) {
      logger.error("Exception occurred while cookie is alphanumeric or not:{} ", cookie);
      throw new Exception("Exception occurred while cookie is alphanumeric or not");
    }
    Matcher m = p.matcher(cookie);
    return m.matches();
  }

  public static void recordValidation(List<String[]> records) throws Exception {
    if (records.isEmpty()) {
      logger.error("Exception occurred while records empty:{} ", records);
      throw new Exception("EMPTY_RECORD_DAY_CSV_FILE_ERROR_ERROR_MESSAGE");
    }
  }
}
