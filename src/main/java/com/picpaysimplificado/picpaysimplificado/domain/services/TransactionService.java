package com.picpaysimplificado.picpaysimplificado.domain.services;

import com.picpaysimplificado.picpaysimplificado.domain.entity.transaction.Transaction;
import com.picpaysimplificado.picpaysimplificado.domain.entity.user.User;
import com.picpaysimplificado.picpaysimplificado.domain.web.dto.TransactionDTO;
import com.picpaysimplificado.picpaysimplificado.infra.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    AuthorizationService authService;


    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender,transaction.value());

        boolean isAuthorized = this.authService.authorizeTransaction(sender,transaction.value());

        if(!isAuthorized){
            throw new Exception("Transação não autorizada");
        }

        var newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.notification(sender,"Transação enviada com sucesso!");
        this.notificationService.notification(receiver,"Transação recebida com sucesso!");

        return newTransaction;
    }


}
