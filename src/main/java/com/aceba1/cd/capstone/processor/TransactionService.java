package com.aceba1.cd.capstone.processor;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.StatelessSessionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class TransactionService {
  @Autowired
  TransactionRepository repository;
  @Autowired
  private SessionFactory sessionFactory;

//  public Transaction findBy() {
//
//  }

  public Transaction findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public List<Transaction> getAll() {
    return repository.findAll();
  }

  public long saveAll(Iterator<Transaction> transactions) {
    StatelessSession session = sessionFactory.openStatelessSession();
    org.hibernate.Transaction tx = session.beginTransaction();
    long count = 0;
    while (transactions.hasNext()) {
      session.insert(transactions.next());
      count++;
    }
    tx.commit();
    session.close();
    return count;
  }

  public void save(Transaction transaction) {
    repository.save(transaction);
  }

//  public void saveAll(Iterable<Transaction> transactions) {
//    repository.saveAll(transactions);
//  }

  public void delete(Transaction transaction) {
    repository.delete(transaction);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public long getSize() {
    return repository.count();
  }

  public void loadCSV() {
  }
}
