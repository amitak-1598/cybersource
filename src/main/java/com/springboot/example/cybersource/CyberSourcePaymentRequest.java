package com.springboot.example.cybersource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CyberSourcePaymentRequest {

    public static void main(String[] args) throws Exception {
        String url = "https://apitest.cybersource.com/pts/v2/payments";

        // Your JWT token
        String bearerToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsInYtYy1tZXJjaGFudC1pZCI6IjEyMzQ1Nl8xNzYzNzkzNTMxIiwieDVjIjpbIk1JSUNhRENDQWRHZ0F3SUJBZ0lXTVRjMk16Z3dOVFUxTURFNE56QXlORGszT0RReE5qQU5CZ2txaGtpRzl3MEJBUXNGQURBZU1Sd3dHZ1lEVlFRRERCTkRlV0psY2xOdmRYSmpaVU5sY25SQmRYUm9NQjRYRFRJMU1URXlNakE1TlRreE1Gb1hEVEk0TVRFeU1qQTVOVGt4TUZvd1BURWFNQmdHQTFVRUF3d1JNVEl6TkRVMlh6RTNOak0zT1RNMU16RXhIekFkQmdOVkJBVVRGakUzTmpNNE1EVTFOVEF4T0Rjd01qUTVOemcwTVRZd2dnRWlNQTBHQ1NxR1NJYjNEUUVCQVFVQUE0SUJEd0F3Z2dFS0FvSUJBUUNuV3JYTkFzOHRySkVpN1FVSDJGZ296dVNkMkJuZ2hzeC9OdDRpS2FjUnFrUnRWS1grSTRoWFlwbHNwQUR1U0cxOStESHBDTGE5R1lRSS9DL2hKNXRmUnVQOS8yM21mL1pRWmxOc1FjTXBvMENQSTVUME52dS9WNHNacXhXRkErTm1hNlY0L0tvWmxtdXRFQ3RtaFM5Zlp0NVFsbjh5WjNCOEoydktqUVNHWCtXeGJON0NJTVIycndpYzduT1lWdjg0R2VrS3YyN1RwOG5DckdyRUpvbTNZYlI5aktoaXhQWlRCMkE3OEpML2pIM2hmTVY2cXA5UUY5c3psZDlISzlzeEEzdjVkeVVGUzZWcFA2bkJaMlU3d3gzeVI3Y0kzcGRUV2pQQjBINzFERnZXN3RPVHVIVkR5NDZ5c2hZVHBRTVBReTUveDIvQUE2LzNKdVc0RWxBM0FnTUJBQUV3RFFZSktvWklodmNOQVFFTEJRQURnWUVBYmZxYWR5cjVxWkJQTjg2Uzh2ZFNORTE3dUNvN0xVRitMTWZjMU1lNThEZFdKMEJvZ3RyZjloMEdpcUJiZmxjMkZHaVpreEp0U1lCSE9FSWx4YlEvVXRrdU8xOUJGSzFOVUZYc2tNTzhZNUVmRDQ5bXNJR25ieW5IcWoxZUo3Y1VJSjZqd0RSSVZ6SmxTdjVOcU9WaVJhVWcxRXVOeFVhZEVSZVlmVnpsaUcwPSJdfQ==.eyJkaWdlc3QiOiIxd2s1SGxzMXFjYlp0allEbGRHeWwzQjJpOTJ2NHJPcVh5eDZ0d1Jib0VzPSIsImRpZ2VzdEFsZ29yaXRobSI6IlNIQS0yNTYiLCJpYXQiOiJUdWUsIDI1IE5vdiAyMDI1IDA2OjE2OjIzIEdNVCJ9.MvDKHU+9a6jJUuHdSBxQ3TiwXp376urh97h6POT1o2E75au1WNnTly38UQoRzgZTLi02m2N01p/UJIu3fB/bqf7e8tWQlj/yyNiZxq8W6tiXelrVfNFHbB6p6puSiACKxKUIne6/mfg0Lj3gsWHUK2qzViuIm9C93XtaAdUdnN5MLDEOvIvWLSugdAQr+OKXVLSkXJYxw413r4H2EuiJ8NwpKPWD/Bz7Mw8bNuLUvrfvh84nPSJ8Cr4jnEry5e5jVecOZbtFn/kkUBOVBvZKTshS1m5YIAvFRSOcXJ0cy6iz1pQpnll5JvyR+9jQsoWgnfYTyQjVSzAoZxqbDfDCSQ==";

        // JSON request body
        String jsonBody = """
        {
          "clientReferenceInformation": {
            "code": "TC50171_3"
          },
          "paymentInformation": {
            "card": {
              "number": "4111111111111111",
              "expirationMonth": "12",
              "expirationYear": "2031"
            }
          },
          "orderInformation": {
            "amountDetails": {
              "totalAmount": "102.21",
              "currency": "USD"
            },
            "billTo": {
              "firstName": "John",
              "lastName": "Doe",
              "address1": "1 Market St",
              "locality": "San Francisco",
              "administrativeArea": "CA",
              "postalCode": "94105",
              "country": "US",
              "email": "test@cybs.com",
              "phoneNumber": "4158880000"
            }
          }
        }
        """;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + bearerToken)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}

