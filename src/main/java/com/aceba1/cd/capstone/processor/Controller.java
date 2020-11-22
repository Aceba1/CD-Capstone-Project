package com.aceba1.cd.capstone.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

@RestController
public class Controller {

  @Autowired
  TransactionService database;

  @GetMapping("/")
  public String main() {
    return "Helloo!";
  }


  @GetMapping("/test/db")
  public Object getItem(
    @RequestParam(required = false) Long id
  ) {
    if (id != null) {
      return database.findById(id);
    }
    return database.getAll();
  }

  @PostMapping("/test/db/csv")
  public Object uploadCSV(
    @RequestBody String csv,
    HttpServletResponse response
    //@RequestBody MultipartFile csv
  ) {
    try {
      database.saveAll(CSVReader.readFromCSV(new StringReader(csv)));
    } catch (Exception e) {
      System.out.println(e.toString());
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      return e.getMessage();
    }
    return database.getSize();
  }

  @PostMapping("/test/db")
  public long postItem(
    @RequestBody Transaction transaction
  ) {
    database.save(transaction);
    return database.getSize();
  }

  @PutMapping("/test/db")
  public long putItem(
    @RequestBody Transaction transaction
  ) {
    database.save(transaction);
    return database.getSize();
  }

  @DeleteMapping("/test/db")
  public long deleteItem(
    @RequestParam(required = false) Long id
  ) {
    database.deleteById(id);
    return database.getSize();
  }
}
