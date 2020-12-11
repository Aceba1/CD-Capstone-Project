package com.aceba1.cd.capstone.processor.controller;

import com.aceba1.cd.capstone.processor.utils.CSVReader;
import com.aceba1.cd.capstone.processor.model.Transaction;
import com.aceba1.cd.capstone.processor.service.TransactionService;
import com.aceba1.cd.capstone.utils.MapBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;

@RestController
public class Controller {

  @Autowired
  TransactionService database;

  @GetMapping("${proc.map.tr.page}")
  public Object getPage(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size
  ) {
    try {
      Page<Transaction> pageTr = database.getPage(PageRequest.of(page, size));

      return new MapBuilder()
        .put("data", pageTr.getContent())
        .put("currentPage", page)
        .put("itemsPerPage", pageTr.getSize())
        .put("totalItems", pageTr.getTotalElements())
        .put("totalPages", pageTr.getTotalPages());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("${proc.map.tr.count}")
  public Object getCount() {
    return new MapBuilder("databaseSize", database.getSize());
  }

  @PostMapping("${proc.map.tr.upload.csv}")
  public Object uploadCSV(
    @RequestBody String csv
    //@RequestBody MultipartFile csv
  ) {
    try {
      long count = database.saveAll(CSVReader.readFromCSV(new StringReader(csv)));

      return new ResponseEntity<>(new MapBuilder(
        "databaseSize", database.getSize(),
        "csvSize", count),
        HttpStatus.CREATED);

    } catch (Exception e) {
      e.printStackTrace();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new MapBuilder(
          "error", "Failed to read CSV file",
          "message", e.getMessage()));
    }
  }

  @PostMapping("${proc.map.tr.single}")
  public Object postItem(
    @RequestBody Transaction transaction
  ) {
    database.save(transaction);
    return getCount();
  }

  @PutMapping("${proc.map.tr.single}")
  public Object putItem(
    @RequestBody Transaction transaction
  ) {
    database.save(transaction);
    return getCount();
  }

  @DeleteMapping("${proc.map.tr.single}")
  public Object deleteItem(
    @RequestParam Long id
  ) {
    if (database.deleteById(id))
      return getCount();
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(getCount());
  }

  @GetMapping("${proc.map.tr.single}")
  public Object getItem(
    @RequestParam Long id
  ) {
    Transaction transaction = database.findById(id);
    if (transaction != null)
      return transaction;
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
