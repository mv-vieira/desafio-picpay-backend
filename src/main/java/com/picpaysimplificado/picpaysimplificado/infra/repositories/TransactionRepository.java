package com.picpaysimplificado.picpaysimplificado.infra.repositories;

import com.picpaysimplificado.picpaysimplificado.domain.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
