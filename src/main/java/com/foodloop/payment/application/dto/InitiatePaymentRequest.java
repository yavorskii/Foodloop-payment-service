package com.foodloop.payment.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class InitiatePaymentRequest {
    private UUID orderId;
    private UUID customerId;
    private UUID paymentMethodId;
    private BigDecimal amount;
    private String currency;

    public InitiatePaymentRequest() {}


    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }

    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }

    public UUID getPaymentMethodId() { return paymentMethodId; }
    public void setPaymentMethodId(UUID paymentMethodId) { this.paymentMethodId = paymentMethodId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
