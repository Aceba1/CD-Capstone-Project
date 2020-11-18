package com.aceba1.cd.capstone.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
  @Autowired
  TransactionRepository repository;

  public Transaction findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public List<Transaction> getAll() {
    return repository.findAll();
  }

  public void save(Transaction transaction) {
    repository.save(transaction);
  }

  public void delete(Transaction transaction) {
    repository.delete(transaction);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public long getSize() {
    return repository.count();
  }
}
