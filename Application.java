package com.coding.task.quantcast;

import static com.coding.task.quantcast.csvreader.CsvReader.readRecordsLineByLine;

import com.coding.task.quantcast.validators.CsvFileValidator;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class Application {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    if (args.length < 4 || args.length > 5) {
      logger.error("Exception occurred while date is empty:{} ", args);
      throw new Exception("Invalid command line, exactly four argument required");
    }
    String date = null;
    String filePath = null;

    for (int i = 0; i < args.length; i = i + 2) {

      if (args[i].equals("-d")) {
        date = args[i + 1];
      }

      if (args[i].equals("-f")) {
        filePath = args[i + 1];
      }
    }

    if (date == null && filePath == null || date == null || filePath == null) {
      logger.error("Exception occurred while date and file path  is invalid:{} ", filePath, date);
      throw new Exception("Exception occurred while date and file path  is invalid");
    }

    CsvFileValidator.validateDate(date);
    CsvFileValidator.validateFileExtension(filePath);
    readRecordsLineByLine(filePath, date);
  }
}


