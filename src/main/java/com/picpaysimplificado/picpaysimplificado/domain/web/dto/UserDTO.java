package com.picpaysimplificado.picpaysimplificado.domain.web.dto;

import com.picpaysimplificado.picpaysimplificado.domain.entity.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName,
                      String lastName,
                      String document,
                      BigDecimal balance,
                      String email,
                      String password,
                      UserType userType) {
}
