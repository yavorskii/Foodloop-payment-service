package com.foodloop.payment.presentation.api;

import com.foodloop.payment.application.PaymentApplicationService;
import com.foodloop.payment.application.dto.InitiatePaymentRequest;
import com.foodloop.payment.application.dto.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentApplicationService paymentApplicationService;

    public PaymentController(PaymentApplicationService paymentApplicationService) {
        this.paymentApplicationService = paymentApplicationService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody InitiatePaymentRequest request) {
        PaymentResponse response = paymentApplicationService.initiatePayment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
