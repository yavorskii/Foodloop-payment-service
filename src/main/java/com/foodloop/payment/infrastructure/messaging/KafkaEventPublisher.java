package com.foodloop.payment.infrastructure.messaging;

import com.foodloop.payment.domain.model.Payment;
import com.foodloop.payment.domain.port.EventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "payment.events";

    public KafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishPaymentInitiated(String eventId, Payment payment) {
        kafkaTemplate.send(TOPIC, eventId, createPayload("PaymentInitiated", payment));
    }

    @Override
    public void publishPaymentSucceeded(String eventId, Payment payment) {
        kafkaTemplate.send(TOPIC, eventId, createPayload("PaymentSucceeded", payment));
    }

    @Override
    public void publishPaymentFailed(String eventId, Payment payment) {
        kafkaTemplate.send(TOPIC, eventId, createPayload("PaymentFailed", payment));
    }

    private Object createPayload(String eventType, Payment payment) {
        return new Object() {
            public final String type = eventType;
            public final String paymentId = payment.getId().toString();
            public final String orderId = payment.getOrderId().toString();
            public final String status = payment.getStatus().name();
            public final java.math.BigDecimal amount = payment.getAmount();
        };
    }
}
