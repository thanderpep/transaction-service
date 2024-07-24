package br.com.caju.infrastructure.dto.request;

import java.math.BigDecimal;

public record ProcessTransactionRequest(String account, BigDecimal totalAmount, String mcc, String merchant) {
}
