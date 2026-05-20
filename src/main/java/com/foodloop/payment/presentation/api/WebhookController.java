package com.foodloop.payment.presentation.api;

import com.foodloop.payment.application.PaymentApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/webhooks")
public class WebhookController {

    private final PaymentApplicationService paymentApplicationService;

    public WebhookController(PaymentApplicationService paymentApplicationService) {
        this.paymentApplicationService = paymentApplicationService;
    }

    @PostMapping("/stripe")
    public ResponseEntity<Void> handleStripeWebhook(
            @RequestHeader("Stripe-Signature") String signature,
            @RequestBody Map<String, Object> payload) {
        
        String transactionId = payload.getOrDefault("transactionId", "unknown").toString();
        boolean isSuccess = "succeeded".equals(payload.get("type"));

        try {
            paymentApplicationService.handleStripeWebhook(transactionId, isSuccess);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
