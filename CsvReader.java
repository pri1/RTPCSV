package com.coding.task.quantcast.csvreader;


import static com.coding.task.quantcast.constants.Constants.COOKIE;
import static com.coding.task.quantcast.constants.Constants.MISSING_CSV_HEADER_OR_INVALID_FORMAT_ERROR_MESSAGE;
import static com.coding.task.quantcast.constants.Constants.TIMESTAMP;

import com.coding.task.quantcast.validators.CsvFileValidator;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CsvReader {

  private static final Logger logger = LoggerFactory.getLogger(CsvReader.class);

  public static void readRecordsLineByLine(String path, String input)
      throws Exception {

    boolean flag = false;

    String line = "";
    String splitBy = ",";

    List<String[]> records = new ArrayList<>();

    Map<String, Integer> map = new HashMap<>();

    try {
      BufferedReader br = new BufferedReader(new FileReader(path));
      String header[] = br.readLine().split(splitBy);

      if (!(header[0].equals(COOKIE) && header[1].equals(TIMESTAMP))) {
        throw new Exception(MISSING_CSV_HEADER_OR_INVALID_FORMAT_ERROR_MESSAGE);
      }
      while ((line = br.readLine()) != null) {
        String[] record = line.split(splitBy);
        CsvFileValidator.isAlphaNumeric(record[0]);
        String date = CsvFileValidator.dateValidation(record[1]);
        if (!date.equals(input)) {
          if (flag == true) {
            break;
          }
          continue;
        }

        if (record[0].length() == 16) {
          records.add(new String[]{record[0], date});
          flag = true;
        }
      }
    } catch (IOException e) {
      logger.error("Exception occurred while Could not find file: ");
      throw new FileNotFoundException("Could not find file: " + path);
    }

    CsvFileValidator.recordValidation(records);
    for (String record[] : records) {

      record[1] = record[1];

      if (record[1].equals(input)) {

        if (!map.containsKey(record[0])) {
          map.put(record[0], 1);
        } else {
          map.put(record[0], map.getOrDefault(record[0], 0) + 1);
        }

      } else {
        break;
      }

    }

    PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) ->
        b.getValue() - a.getValue());

    pq.addAll(map.entrySet());

    int count1 = 0;
    if (!pq.isEmpty()) {
      count1 = pq.peek().getValue();
    } 
    for (Map.Entry<String, Integer> entry : pq) {

      int count2 = entry.getValue();
      if (count2 == count1) {
        System.out.println(entry.getKey());
      }
    }
  }

}
