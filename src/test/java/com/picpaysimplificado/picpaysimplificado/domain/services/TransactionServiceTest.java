package com.picpaysimplificado.picpaysimplificado.domain.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TransactionServiceTest {

    @Test
    @DisplayName("Should create transaction successfully when everything is OK")
    void createTransactionSuccess() {
    }

    @Test
    @DisplayName("Should throw Exception when transaction is not allowed ")
    void createTransactionError() {
    }
}
