package com.aceba1.cd.capstone.processor;

import com.aceba1.cd.capstone.utils.MapBuilder;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
  public Object getItem(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size,
    @RequestParam(required = false) Long id
  ) {
    try {

      if (id != null)
        return database.findById(id);
      else {
        Page<Transaction> pageTr = database.repository.findAll(PageRequest.of(page, size));

        return new MapBuilder()
          .put("data", pageTr.getContent())
          .put("currentPage", page)
          .put("itemsPerPage", pageTr.size)
          .put("totalItems", pageTr.getTotalElements())
          .put("totalPages", pageTr.getTotalPages());
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("${proc.map.tr.count}")
  public Object getCount() {
    return new MapBuilder("databaseSize", database.getSize());
  }

  @PostMapping("${proc.map.tr.upload}")
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
      System.out.println(e.toString());

      return new ResponseEntity<>(new MapBuilder(
          "error", "Failed to read CSV file",
          "message", e.getMessage()),
        HttpStatus.BAD_REQUEST);
    }
  }

  // @PostMapping("/test/db")
  // public Object postItem(
  //   @RequestBody Transaction transaction
  // ) {
  //   database.save(transaction);
  //   return getCount();
  // }

  // @PutMapping("/test/db")
  // public Object putItem(
  //   @RequestBody Transaction transaction
  // ) {
  //   database.save(transaction);
  //   return getCount();
  // }

  // @DeleteMapping("/test/db")
  // public Object deleteItem(
  //   @RequestParam(required = false) Long id
  // ) {
  //   database.deleteById(id);
  //   return getCount();
  // }
}
