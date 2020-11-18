package com.aceba1.cd.capstone.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
