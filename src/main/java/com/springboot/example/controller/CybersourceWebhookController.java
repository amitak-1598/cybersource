package com.springboot.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.example.service.WebhookService;
import com.springboot.example.utility.SignatureVerifier;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cybersource")
public class CybersourceWebhookController {

	@Autowired
	private SignatureVerifier signatureVerifier;

	@Autowired
	private WebhookService webhookService;

	@PostMapping("/webhook")
	public ResponseEntity<String> handleWebhook(@RequestBody String payload, @RequestHeader Map<String, String> headers,
			HttpServletRequest request) {

		try {
			
			boolean valid = signatureVerifier.verify(headers, payload, request);

			if (!valid) {
				System.out.println("❌ Invalid CyberSource signature");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Signature");
			}

			
			webhookService.processEvent(payload);

			
			return ResponseEntity.ok("Webhook processed successfully");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing webhook");
		}
	}
	
	@PostMapping("/return")
	public ResponseEntity<String> receive3DSResponse(@RequestParam Map<String, String> payload) {
	    System.out.println("=== 3DS Response Received ===");
	    payload.forEach((k, v) -> System.out.println(k + " : " + v));

	    return ResponseEntity.ok("3DS Response received successfully!");
	}
}
