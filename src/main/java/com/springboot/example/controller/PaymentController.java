package com.springboot.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.example.model.CapturePaymentRequest;
import com.springboot.example.model.PayerAuthenticationRequest;
import com.springboot.example.model.PaymentRequest;
import com.springboot.example.model.RefundPaymentRequest;
import com.springboot.example.service.CyberSourceService;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private CyberSourceService cyberSourceService;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest body ) {
        try {
        	
        	System.out.println("Received Payment Request: " + body);
        	
        	
            String response = cyberSourceService.processPayment(body);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    
    
    @PostMapping("/capture/{authorizationId}")
    public ResponseEntity<?> capturePayment(
            @PathVariable ("authorizationId") String authorizationId,
            @RequestBody CapturePaymentRequest captureRequest) {
        try {
            System.out.println("Capture Request for AuthorizationId: " + authorizationId);
            String response = cyberSourceService.capturePayment(authorizationId, captureRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/refund/{paymentId}")
    public ResponseEntity<?> refundPayment(
            @PathVariable ("paymentId") String paymentId,
            @RequestBody RefundPaymentRequest refundRequest) {

        try {
            System.out.println("Refund Request Received for Payment: " + paymentId);
            System.out.println("Payload: " + refundRequest.toJson());

            String response = cyberSourceService.refundPayment(paymentId, refundRequest);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/authentication-setup")
    public ResponseEntity<?> setupAuthentication(@RequestBody PayerAuthenticationRequest payload) {
        try {
            String response = cyberSourceService.setupAuthentication(payload);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/payer-auth")
    public ResponseEntity<?> payerAuthentication(@RequestBody PayerAuthenticationRequest request) {
        try {
            System.out.println("Payer Authentication Request: " + request.toJson());
            String response = cyberSourceService.payerAuthentication(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }}
        
    @PostMapping("/validate")
    public ResponseEntity<?> validateAuthentication(@RequestBody Map<String, Object> payload) {
        try {
            String response = cyberSourceService.validatePayerAuthentication(payload);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
