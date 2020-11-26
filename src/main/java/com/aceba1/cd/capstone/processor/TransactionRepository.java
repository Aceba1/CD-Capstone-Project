package com.aceba1.cd.capstone.processor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  //Page<Transaction> findBy____(____ ____, Pageable pageable);
}
