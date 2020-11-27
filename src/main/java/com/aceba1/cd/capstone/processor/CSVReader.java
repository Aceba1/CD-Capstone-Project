package com.aceba1.cd.capstone.processor;

import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.stream.Stream;

public class CSVReader {
  //TODO: Expand to beyond Transaction class

  public static Stream<Transaction> readFromCSV(Reader csvFile) throws IOException {
    BufferedReader reader = new BufferedReader(csvFile);

    int[] columns = Transaction.indexesOf(reader.readLine().split(","));

    return reader
      .lines()
      .map(c -> CSVReader.readString(c, columns));
  }

  public static Transaction readString(String row, int[] index) {
    String[] items = row.split(",");
    return new Transaction(
      Integer.parseInt(items[index[0]]),
      items[index[1]],
      Double.parseDouble(items[index[2]]),
      items[index[3]],
      Double.parseDouble(items[index[4]]),
      Double.parseDouble(items[index[5]]),
      items[index[6]],
      Double.parseDouble(items[index[7]]),
      Double.parseDouble(items[index[8]]),
      Integer.parseInt(items[index[9]]),
      Integer.parseInt(items[index[10]]));
  }

}
