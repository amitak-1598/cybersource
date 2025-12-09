package com.springboot.example.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.example.model.CapturePaymentRequest;
import com.springboot.example.model.PayerAuthenticationRequest;
import com.springboot.example.model.PaymentRequest;
import com.springboot.example.model.RefundPaymentRequest;

@Service
public class CyberSourceService {

	private final String authenticationSetupUrl = "https://apitest.cybersource.com/risk/v1/authentication-setups";
	private final String authUrl = "https://apitest.cybersource.com/risk/v1/authentications";
	private final String authResultsUrl = "https://apitest.cybersource.com/risk/v1/authentication-results";
	private final String url = "https://apitest.cybersource.com/pts/v2/payments";
	private final String host = "apitest.cybersource.com";
	private final String apiKeyId = "fe16ccc1-eb17-4ca7-b4c0-041fdef9c941";
	private final String secretKey = "r5x2Ll053H7XRsUUhf/eAIYLKJVBnhPBBEjiz8pMSb4=";
	private final String merchantId = "123456_1763793531";

	
	// process Payment
	public String processPayment(PaymentRequest requests) throws Exception {

		String body = requests.toJson();

		String vCDate = getCurrentDate();

		String digest = computeDigest(body);
		System.out.println("Digest: " + digest);

		String requestTarget = "post /pts/v2/payments";

		String signature = computeSignature(host, vCDate, digest, requestTarget, merchantId, apiKeyId, secretKey);
		System.out.println("Signature: " + signature);

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("v-c-merchant-id", merchantId)
				.header("v-c-date", vCDate).header("Digest", digest).header("Signature", signature)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println("Cybersource Response: " + response.body());
		return response.body();
	}
	
	
	// capture Payment
	public String capturePayment(String authorizationId, CapturePaymentRequest captureRequest) throws Exception {

		String body = captureRequest.toJson();

		String vCDate = getCurrentDate();

		String digest = computeDigest(body);

		String requestTarget = "post /pts/v2/payments/" + authorizationId + "/captures";

		String signature = computeSignature(host, vCDate, digest, requestTarget, merchantId, apiKeyId, secretKey);

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/" + authorizationId + "/captures"))
				.header("v-c-merchant-id", merchantId).header("v-c-date", vCDate).header("Digest", digest)
				.header("Signature", signature).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response.body();
	}

	
	// refund Payment
	public String refundPayment(String paymentId, RefundPaymentRequest refundRequest) throws Exception {

		String body = refundRequest.toJson();

		String vCDate = getCurrentDate();

		String digest = computeDigest(body);

		String requestTarget = "post /pts/v2/payments/" + paymentId + "/refunds";

		String signature = computeSignature(host, vCDate, digest, requestTarget, merchantId, apiKeyId, secretKey);

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/" + paymentId + "/refunds"))
				.header("v-c-merchant-id", merchantId).header("v-c-date", vCDate).header("Digest", digest)
				.header("Signature", signature).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response.body();
	}

	
	// payer Setup Authentication
	public String setupAuthentication(PayerAuthenticationRequest requestPayload) throws Exception {

		String body = new ObjectMapper().writeValueAsString(requestPayload);

		String vCDate = getCurrentDate();

		String digest = computeDigest(body);

		String requestTarget = "post /risk/v1/authentication-setups";

		String signature = computeSignature(host, vCDate, digest, requestTarget, merchantId, apiKeyId, secretKey);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(authenticationSetupUrl))
				.header("v-c-merchant-id", merchantId).header("v-c-date", vCDate).header("Digest", digest)
				.header("Signature", signature).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("Authentication Setup Response: " + response.body());
		return response.body();
	}

	
	// Payer Authentication Check Enrollement 
	public String payerAuthentication(PayerAuthenticationRequest requestPayload) throws Exception {

		String body = requestPayload.toJson();

		String vCDate = getCurrentDate();

		String digest = computeDigest(body);

		String requestTarget = "post /risk/v1/authentications";

		String signature = computeSignature(host, vCDate, digest, requestTarget, merchantId, apiKeyId, secretKey);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(authUrl)).header("v-c-merchant-id", merchantId)
				.header("v-c-date", vCDate).header("Digest", digest).header("Signature", signature)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("Payer Authentication Response: " + response.body());
		return response.body();
	}

	
	// Payment Validation
	public String validatePayerAuthentication(Map<String, Object> payload) throws Exception {

		String body = new ObjectMapper().writeValueAsString(payload);

		String vCDate = getCurrentDate();
		String digest = computeDigest(body);
		String requestTarget = "post /risk/v1/authentication-results";
		String signature = computeSignature(host, vCDate, digest, requestTarget, merchantId, apiKeyId, secretKey);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(authResultsUrl))
				.header("v-c-merchant-id", merchantId).header("v-c-date", vCDate).header("Digest", digest)
				.header("Signature", signature).header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}

	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.format(new Date());
	}

	private String computeDigest(String body) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digestBytes = md.digest(body.getBytes(StandardCharsets.UTF_8));
		return "SHA-256=" + Base64.getEncoder().encodeToString(digestBytes);
	}

	private String computeSignature(String host, String vCDate, String digest, String requestTarget, String merchantId,
			String apiKeyId, String secretKey) throws Exception {

		String signatureString = "host: " + host + "\n" + "v-c-date: " + vCDate + "\n" + "(request-target): "
				+ requestTarget + "\n" + "digest: " + digest + "\n" + "v-c-merchant-id: " + merchantId;

		byte[] decodedSecret = Base64.getDecoder().decode(secretKey);
		SecretKeySpec keySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");

		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(keySpec);
		byte[] rawHmac = mac.doFinal(signatureString.getBytes(StandardCharsets.UTF_8));

		String signatureBase64 = Base64.getEncoder().encodeToString(rawHmac);

		return "keyid=\"" + apiKeyId
				+ "\", algorithm=\"HmacSHA256\", headers=\"host v-c-date (request-target) digest v-c-merchant-id\", signature=\""
				+ signatureBase64 + "\"";
	}

}