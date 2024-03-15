package com.picpaysimplificado.picpaysimplificado.domain.web.dto;

import java.math.BigDecimal;

public record TransactionDTO(
        BigDecimal value,
        Long senderId,
        Long receiverId

) {
}
