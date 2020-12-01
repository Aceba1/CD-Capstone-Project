package com.aceba1.cd.capstone.processor.service;

import com.aceba1.cd.capstone.processor.entity.Transaction;
import com.aceba1.cd.capstone.processor.repository.TransactionRepository;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TransactionService {
  @Autowired
  TransactionRepository repository;
  @Autowired
  private SessionFactory sessionFactory;


  public Page<Transaction> getPage(Pageable page) {
    return repository.findAll(page);
  }

  public Transaction findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public List<Transaction> getAll() {
    return repository.findAll();
  }

//  public long saveAll(Reader input) {
//    CSVReader reader = new CSVReader(input);
//    for(String[] nextLine : reader.iterator()) {
//    }
//  }

  public long saveAll(Stream<Transaction> transactions) {
    StatelessSession session = sessionFactory.openStatelessSession();
    org.hibernate.Transaction tx = session.beginTransaction();
    //AtomicLong count = new AtomicLong(0);

    //count.incrementAndGet();
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    var iter = transactions.iterator();
    while (iter.hasNext()) {
      iter.next();
    }
    transactions.forEach(session::insert);

    tx.commit();
    session.close();

    stopWatch.stop();

    System.out.println("CSV : Inserted \" + count + \" items in " + stopWatch.getTotalTimeMillis() + " MS");

    //TODO: RESOLVE PLACEHOLDER
    return -420; //count.longValue();
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
