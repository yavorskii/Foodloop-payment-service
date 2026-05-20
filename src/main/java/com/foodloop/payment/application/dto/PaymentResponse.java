package com.foodloop.payment.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentResponse {
    private UUID id;
    private String status;
    private String gatewayTransactionId;
    private LocalDateTime createdAt;

    public PaymentResponse() {}

    public PaymentResponse(UUID id, String status, String gatewayTransactionId, LocalDateTime createdAt) {
        this.id = id;
        this.status = status;
        this.gatewayTransactionId = gatewayTransactionId;
        this.createdAt = createdAt;
    }


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getGatewayTransactionId() { return gatewayTransactionId; }
    public void setGatewayTransactionId(String gatewayTransactionId) { this.gatewayTransactionId = gatewayTransactionId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
