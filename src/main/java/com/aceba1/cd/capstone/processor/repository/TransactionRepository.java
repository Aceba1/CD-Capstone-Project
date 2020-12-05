package com.aceba1.cd.capstone.processor.repository;

import com.aceba1.cd.capstone.processor.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  //Page<Transaction> findBy____(____ ____, Pageable pageable);
}
