package com.picpaysimplificado.picpaysimplificado.domain.services;

import com.picpaysimplificado.picpaysimplificado.domain.entity.user.User;
import com.picpaysimplificado.picpaysimplificado.domain.entity.user.UserType;
import com.picpaysimplificado.picpaysimplificado.domain.web.dto.TransactionDTO;
import com.picpaysimplificado.picpaysimplificado.infra.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class TransactionServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private TransactionRepository repository;
    @Mock
    private NotificationService notificationService;
    @Mock
    AuthorizationService authService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is OK")
    void createTransactionSuccess() throws Exception {
        User sender = new User(
                1L,
                "Matheus",
                "Vicente",
                "12345678900",
                "matheus@example.com",
                "123456789",
                new BigDecimal(100),
                UserType.COMMON
        );
        User receiver = new User(
                2L,
                "Mikaela",
                "Marques",
                "12345678910",
                "mikaela@example.com",
                "123456789",
                new BigDecimal(100),
                UserType.COMMON
        );

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(100), sender.getId(), receiver.getId());
        transactionService.createTransaction(request);

        verify(repository,times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(200));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).notification(sender, "Transação enviada com sucesso!");
        verify(notificationService, times(1)).notification(receiver, "Transação recebida com sucesso!");
    }

    @Test
    @DisplayName("Should throw Exception when transaction is not allowed ")
    void createTransactionError() {
    }
}
