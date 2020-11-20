package com.aceba1.cd.capstone.processor;

import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class CSVReader {
  //TODO: Expand to beyond Transaction class

  public static Iterable<Transaction> readFromCSV(Reader csvFile) {
    return new BufferedReader(csvFile)
      .lines()
      .skip(1)
      .map(CSVReader::readString)
      ::iterator;
  }

  public static Iterable<Transaction> readFromCSV(MultipartFile csvFile) {

    try {
      return new BufferedReader(
        new InputStreamReader(
          csvFile.getInputStream()
        )
      )
        .lines()
        .skip(1)
        .map(CSVReader::readString)
        ::iterator;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return  null;
  }

  public static Transaction readString(String row) {
    String[] items = row.split(",");
    return new Transaction(
      Integer.parseInt(items[0]),
      items[1],
      Long.parseLong(items[2]),
      items[3],
      Long.parseLong(items[4]),
      Long.parseLong(items[5]),
      items[6],
      Long.parseLong(items[7]),
      Long.parseLong(items[8]),
      Integer.parseInt(items[9]),
      Integer.parseInt(items[10]));
  }

}
