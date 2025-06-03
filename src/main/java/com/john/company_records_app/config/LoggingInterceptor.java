package com.john.company_records_app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public @NonNull ClientHttpResponse intercept(
            @NonNull HttpRequest request,
            @NonNull byte[] body,
            @NonNull ClientHttpRequestExecution execution) throws IOException {

        logRequestDetails(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponseDetails(response);
        return response;
    }


    private void logRequestDetails(HttpRequest request, byte[] body) {
        log.info("➡️ Request URI: {}", request.getURI());
        log.info("➡️ Request Method: {}", request.getMethod());
        log.info("➡️ Request Headers: {}", request.getHeaders());
        log.info("➡️ Request Body: {}", new String(body));
    }

    private void logResponseDetails(ClientHttpResponse response) {
        try {
            log.info("⬅️ Response Status Code: {}", response.getStatusCode().value());
            log.info("⬅️ Response Status Text: {}", response.getStatusText());
            log.info("⬅️ Response Headers: {}", response.getHeaders());

            String responseBody = new BufferedReader(new InputStreamReader(response.getBody()))
                    .lines().collect(Collectors.joining("\n"));
            log.info("⬅️ Response Body: {}", responseBody);
        } catch (IOException e) {
            log.warn("❗ Не вдалося прочитати тіло відповіді", e);
        }
    }
}

