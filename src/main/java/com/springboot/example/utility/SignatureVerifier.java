package com.springboot.example.utility;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class SignatureVerifier {

	@Value("${cybersource.secret.key}")
	private String secretKey;

	public boolean verify(Map<String, String> headers, String body, HttpServletRequest request) {

		try {
			String signatureHeader = headers.get("signature");
			String digestHeader = headers.get("digest");
			String vDate = headers.get("v-c-date");
			String merchantId = headers.get("v-c-merchant-id");
			String host = request.getServerName();
			String path = request.getRequestURI();

			String signedData = "host: " + host + "\n" + "v-c-date: " + vDate + "\n" + "request-target: post " + path
					+ "\n" + "digest: " + digestHeader + "\n" + "v-c-merchant-id: " + merchantId;

			String computedSignature = generateSignature(signedData, secretKey);

			String receivedSignature = extractSignatureValue(signatureHeader);

			return computedSignature.equals(receivedSignature);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private String generateSignature(String data, String secret) throws Exception {
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256"));

		byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

		return Base64.getEncoder().encodeToString(rawHmac);
	}

	private String extractSignatureValue(String header) {

		int start = header.indexOf("signature=\"") + 11;
		int end = header.indexOf("\"", start);
		return header.substring(start, end);
	}
}
