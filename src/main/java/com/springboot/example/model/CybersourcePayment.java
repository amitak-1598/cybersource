package com.springboot.example.model;



import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class CybersourcePayment {

    private static final String MERCHANT_ID = "123456_1763793531";
    private static final String API_KEY_ID = "fe16ccc1-eb17-4ca7-b4c0-041fdef9c941";
    private static final String SECRET_KEY = "r5x2Ll053H7XRsUUhf/eAIYLKJVBnhPBBEjiz8pMSb4=";
    private static final String URL = "https://apitest.cybersource.com/pts/v2/payments";

    public static void main(String[] args) throws Exception {
        String response = processPayment();
        System.out.println("Response: " + response);
    }

    public static String processPayment() throws Exception {

        // ------------------ BODY ------------------
        String body = "{\n" +
                "  \"clientReferenceInformation\": {\"code\": \"test_payment\"},\n" +
                "  \"processingInformation\": {\"commerceIndicator\": \"internet\"},\n" +
                "  \"orderInformation\": {\n" +
                "    \"billTo\": {\n" +
                "      \"firstName\": \"John\",\n" +
                "      \"lastName\": \"Doe\",\n" +
                "      \"address1\": \"1 Market St\",\n" +
                "      \"postalCode\": \"94105\",\n" +
                "      \"locality\": \"san francisco\",\n" +
                "      \"administrativeArea\": \"CA\",\n" +
                "      \"country\": \"US\",\n" +
                "      \"phoneNumber\": \"4158880000\",\n" +
                "      \"company\": \"Visa\",\n" +
                "      \"email\": \"test@cybs.com\"\n" +
                "    },\n" +
                "    \"amountDetails\": {\"totalAmount\": \"102.21\", \"currency\": \"USD\"}\n" +
                "  },\n" +
                "  \"paymentInformation\": {\n" +
                "    \"card\": {\n" +
                "      \"number\": \"4111111111111111\",\n" +
                "      \"expirationMonth\": \"12\",\n" +
                "      \"expirationYear\": \"2031\",\n" +
                "      \"securityCode\": \"123\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        // ------------------ DATE ------------------
        String vCDATE = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

        // ------------------ DIGEST ------------------
        String digest = computeDigest(body);

        // ------------------ REQUEST-TARGET ------------------
        String requestTarget = "post /pts/v2/payments";

        // ------------------ HOST ------------------
        URI uri = new URI(URL);
        String host = uri.getHost();

        // ------------------ SIGNATURE ------------------
        String signature = computeSignature(host, vCDATE, digest, requestTarget, MERCHANT_ID, API_KEY_ID, SECRET_KEY);

        // ------------------ SEND REQUEST ------------------
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("v-c-merchant-id", MERCHANT_ID)
                .header("v-c-date", vCDATE)
                .header("Digest", digest)
                .header("Signature", signature)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static String computeDigest(String body) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(body.getBytes(StandardCharsets.UTF_8));
        return "SHA-256=" + Base64.getEncoder().encodeToString(hash);
    }

    private static String computeSignature(String host, String vCDATE, String digest, String requestTarget,
                                           String merchantId, String apiKeyId, String secretKey) throws Exception {

        // Canonical string
        String signatureString = "host: " + host + "\n" +
                                 "v-c-date: " + vCDATE + "\n" +
                                 "(request-target): " + requestTarget + "\n" +
                                 "digest: " + digest + "\n" +
                                 "v-c-merchant-id: " + merchantId;

        // Decode secret
        byte[] decodedSecret = Base64.getDecoder().decode(secretKey);
        SecretKeySpec keySpec = new SecretKeySpec(decodedSecret, "HmacSHA256");

        // Compute HMAC
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySpec);
        byte[] rawHmac = mac.doFinal(signatureString.getBytes(StandardCharsets.UTF_8));

        String signatureBase64 = Base64.getEncoder().encodeToString(rawHmac);

        // Return signature header
        return "keyid=\"" + apiKeyId + "\", algorithm=\"HmacSHA256\", headers=\"host v-c-date (request-target) digest v-c-merchant-id\", signature=\"" + signatureBase64 + "\"";
    }
}

