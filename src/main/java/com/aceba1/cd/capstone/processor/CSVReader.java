package com.aceba1.cd.capstone.processor;

import com.aceba1.cd.capstone.processor.entity.Transaction;
import org.hibernate.StatelessSession;

import java.io.*;
import java.util.stream.Stream;

public class CSVReader {
  //TODO: Expand to beyond Transaction class

  public static Stream<Transaction> readFromCSV(Reader csvFile) throws IOException {
    BufferedReader reader = new BufferedReader(csvFile);

    int[] columns = Transaction.indexesOf(reader.readLine().split(","));

    Transaction placeholder = new Transaction();

    return reader
      .lines()
      .map(c -> CSVReader.readString(placeholder, c, columns));
  }

  public static void readToSession(StatelessSession session, Reader csvFile) throws IOException {
    BufferedReader reader = new BufferedReader(csvFile);

    int[] columns = Transaction.indexesOf(reader.readLine().split(","));
    var tx = session.beginTransaction();
    String in;
    Transaction placeholder = new Transaction();

    while ((in = reader.readLine()) != null && in.length() != 0)
      session.insert(readString(placeholder, in, columns));
    tx.commit();
  }

  public static Transaction readString(Transaction object, String row, int[] index) {
    String[] items = row.split(",");
    return object.set(
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
