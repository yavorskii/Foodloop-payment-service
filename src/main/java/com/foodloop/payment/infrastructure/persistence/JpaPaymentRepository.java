package com.foodloop.payment.infrastructure.persistence;

import com.foodloop.payment.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByGatewayTransactionId(String gatewayTransactionId);
}
