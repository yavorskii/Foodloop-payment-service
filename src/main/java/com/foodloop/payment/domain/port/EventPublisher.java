package com.foodloop.payment.domain.port;

public interface EventPublisher {
    void publishPaymentInitiated(String eventId, com.foodloop.payment.domain.model.Payment payment);
    void publishPaymentSucceeded(String eventId, com.foodloop.payment.domain.model.Payment payment);
    void publishPaymentFailed(String eventId, com.foodloop.payment.domain.model.Payment payment);
}
