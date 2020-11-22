package com.aceba1.cd.capstone.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

@RestController
public class Controller {

  @Autowired
  TransactionService database;
  @Autowired
  ObjectMapper mapper;

  @GetMapping("/")
  public String main() {
    return "Helloo!";
  }


  //TODO: Paged requests
  // accept a property for specific pages
  // return an object with information for
  // - page size
  // - total page count
  // - total count
  // - array of items in page
  @GetMapping("/test/db")
  public Object getItem(
    @RequestParam(required = false) Long id
  ) {
    if (id != null) {
      return database.findById(id);
    }
    return database.getAll();
  }

  //TODO: Wrap ALL responses in objects (JSON)

  @GetMapping("/test/db/count")
  public ObjectNode getCount() {
    return mapper.createObjectNode()
      .put("databaseSize", database.getSize());
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
      return mapper.createObjectNode()
        .put("error", "Failed to read CSV file")
        .put("status", HttpStatus.BAD_REQUEST.value())
        .put("message", e.getMessage());
    }
    return mapper.createObjectNode()
      .put("databaseSize", database.getSize());
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
