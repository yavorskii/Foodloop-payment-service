package com.foodloop.payment.infrastructure.gateway;

import com.foodloop.payment.domain.port.PaymentGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class StripeGatewayAdapter implements PaymentGateway {

    @Override
    public String charge(BigDecimal amount, String currency, String sourceToken) {
        return "ch_" + UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public String refund(String transactionId, BigDecimal amount) {
        return "re_" + UUID.randomUUID().toString().replace("-", "");
    }
}
