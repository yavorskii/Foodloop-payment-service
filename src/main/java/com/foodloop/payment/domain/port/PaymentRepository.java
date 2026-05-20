package com.foodloop.payment.domain.port;

import com.foodloop.payment.domain.model.Payment;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(UUID id);
    Optional<Payment> findByGatewayTransactionId(String gatewayTransactionId);
}
