package com.picpaysimplificado.picpaysimplificado.domain.services;

import com.picpaysimplificado.picpaysimplificado.domain.entity.user.User;
import com.picpaysimplificado.picpaysimplificado.domain.web.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<Void> notification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

       ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest ,String.class);

       if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
           throw new Exception("Serviço de notificação indisponível no momento");
       }

       return CompletableFuture.completedFuture(null);
    }
}
