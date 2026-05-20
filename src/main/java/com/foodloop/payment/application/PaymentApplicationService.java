package com.foodloop.payment.application;

import com.foodloop.payment.application.dto.InitiatePaymentRequest;
import com.foodloop.payment.application.dto.PaymentResponse;
import com.foodloop.payment.domain.model.Payment;
import com.foodloop.payment.domain.model.PaymentStatus;
import com.foodloop.payment.domain.port.EventPublisher;
import com.foodloop.payment.domain.port.PaymentGateway;
import com.foodloop.payment.domain.port.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PaymentApplicationService {

    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;
    private final EventPublisher eventPublisher;

    public PaymentApplicationService(PaymentRepository paymentRepository, PaymentGateway paymentGateway, EventPublisher eventPublisher) {
        this.paymentRepository = paymentRepository;
        this.paymentGateway = paymentGateway;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public PaymentResponse initiatePayment(InitiatePaymentRequest request) {
        Payment payment = new Payment(
                UUID.randomUUID(),
                request.getOrderId(),
                request.getCustomerId(),
                request.getPaymentMethodId(),
                request.getAmount(),
                request.getCurrency()
        );

        try {
            String transactionId = paymentGateway.charge(payment.getAmount(), payment.getCurrency(), "dummy_token");
            payment.setGatewayTransactionId(transactionId);
            payment.setStatus(PaymentStatus.PENDING);
        } catch (Exception e) {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setErrorMessage(e.getMessage());
        }

        payment = paymentRepository.save(payment);

        eventPublisher.publishPaymentInitiated(UUID.randomUUID().toString(), payment);

        return new PaymentResponse(
                payment.getId(),
                payment.getStatus().name(),
                payment.getGatewayTransactionId(),
                payment.getCreatedAt()
        );
    }

    @Transactional
    public void handleStripeWebhook(String gatewayTransactionId, boolean success) {
        Payment payment = paymentRepository.findByGatewayTransactionId(gatewayTransactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found for gateway transaction ID: " + gatewayTransactionId));

        if (payment.getStatus() != PaymentStatus.PENDING) {
            return;
        }

        if (success) {
            payment.setStatus(PaymentStatus.SUCCESS);
            eventPublisher.publishPaymentSucceeded(UUID.randomUUID().toString(), payment);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            eventPublisher.publishPaymentFailed(UUID.randomUUID().toString(), payment);
        }

        paymentRepository.save(payment);
    }
}
