package com.picpaysimplificado.picpaysimplificado.infra.repositories;

import com.picpaysimplificado.picpaysimplificado.domain.entity.user.User;
import com.picpaysimplificado.picpaysimplificado.domain.web.dto.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static com.picpaysimplificado.picpaysimplificado.domain.entity.user.UserType.COMMON;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should not get user from DB when not exists")
    void findUserByDocumentError() {
        String document = "12310518417";

        Optional<User> result = this.userRepository.findUserByDocument(document);
        assertThat(result.isEmpty()).isTrue();

    }

    @Test
    @DisplayName("Should get user successfully from DB")
    void findUserByDocumentSucess() {
        String document = "12310518417";
        UserDTO data = new UserDTO(
                "Matheus",
                "Vicente",
                document,
                new BigDecimal(100),
                "matheus@example.com",
                "84479635",
                COMMON
        );
        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);
        assertThat(result.isPresent()).isTrue();
    }

    private User createUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        this.entityManager.persist(newUser);
        return newUser;
    }

}