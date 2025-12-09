package com.springboot.example.cybersource;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CybersourceDigest {

	public static String GenerateDigest() throws NoSuchAlgorithmException {
		String bodyText = "{\r\n" + "  \"clientReferenceInformation\": {\r\n" + "    \"code\": \"TC50171_3\"\r\n"
				+ "  },\r\n" + "  \"paymentInformation\": {\r\n" + "    \"card\": {\r\n"
				+ "      \"number\": \"4111111111111111\",\r\n" + "      \"expirationMonth\": \"12\",\r\n"
				+ "      \"expirationYear\": \"2031\"\r\n" + "    }\r\n" + "  },\r\n" + "  \"orderInformation\": {\r\n"
				+ "    \"amountDetails\": {\r\n" + "      \"totalAmount\": \"102.21\",\r\n"
				+ "      \"currency\": \"USD\"\r\n" + "    },\r\n" + "    \"billTo\": {\r\n"
				+ "      \"firstName\": \"John\",\r\n" + "      \"lastName\": \"Doe\",\r\n"
				+ "      \"address1\": \"1 Market St\",\r\n" + "      \"locality\": \"san francisco\",\r\n"
				+ "      \"administrativeArea\": \"CA\",\r\n" + "      \"postalCode\": \"94105\",\r\n"
				+ "      \"country\": \"US\",\r\n" + "      \"email\": \"test@cybs.com\",\r\n"
				+ "      \"phoneNumber\": \"4158880000\"\r\n" + "    }\r\n" + "  }\r\n" + "}";
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(bodyText.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
		return "SHA-256=" + Base64.getEncoder().encodeToString(digest);
	}

	public static void main(String args[]) throws NoSuchAlgorithmException {
		String digest = GenerateDigest();
		System.out.println(digest);
	}
}
