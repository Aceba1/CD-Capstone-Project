package com.aceba1.cd.capstone.database;

import ch.qos.logback.classic.db.SQLBuilder;
import ch.qos.logback.core.db.dialect.PostgreSQLDialect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLData;

public class TransactionsDBService {
  public static void connect() {
    Connection connection = null;
    String host="localhost";
    String port="5433";
    String db_name="postgres";
    String username="postgres";
    String password="root";
    try {
      connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");
      if (connection != null) {
        System.out.println("Connection OK");
        connection.beginRequest();
        var statement = connection.createStatement();
        var query = statement.executeQuery("SELECT * FROM SAMPLE;");
        System.out.println(query.getString(0));
      } else {
        System.out.println("Connection Failed");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
