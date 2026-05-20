package com.foodloop.payment.domain.port;

import java.math.BigDecimal;

public interface PaymentGateway {
    String charge(BigDecimal amount, String currency, String sourceToken);
    String refund(String transactionId, BigDecimal amount);
}
