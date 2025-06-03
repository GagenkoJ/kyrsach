package com.john.company_records_app.service;

import com.john.company_records_app.dto.RegisterCompanyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakAdminClient {

    private final RestTemplate restTemplate;

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    public String getAdminToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret;

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token",
                    new HttpEntity<>(body, headers),
                    Map.class
            );
            return (String) response.getBody().get("access_token");
        } catch (HttpStatusCodeException e) {
            log.error("❌ [Keycloak] Failed to get admin token ({}): {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException("Unable to get admin token from Keycloak");
        } catch (Exception e) {
            log.error("❌ [Keycloak] Unexpected error while getting admin token", e);
            throw new RuntimeException("Unexpected error while getting admin token");
        }
    }

    public String createUser(RegisterCompanyDTO dto, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        Map<String, Object> payload = new HashMap<>();
        payload.put("username", dto.getEmail());
        payload.put("email", dto.getEmail());
        payload.put("firstName", dto.getName());
        payload.put("lastName", dto.getSurname());
        payload.put("enabled", true);

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(
                    keycloakUrl + "/admin/realms/" + realm + "/users",
                    new HttpEntity<>(payload, headers),
                    Void.class
            );

            String location = response.getHeaders().getLocation().toString();
            return location.substring(location.lastIndexOf('/') + 1);
        } catch (HttpStatusCodeException e) {
            log.error("❌ [Keycloak] Failed to create user: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Unable to create user in Keycloak");
        }
    }

    public void setPassword(String userId, String password, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        Map<String, Object> body = new HashMap<>();
        body.put("type", "password");
        body.put("value", password);
        body.put("temporary", false);

        try {
            restTemplate.put(
                    keycloakUrl + "/admin/realms/" + realm + "/users/" + userId + "/reset-password",
                    new HttpEntity<>(body, headers)
            );
        } catch (HttpStatusCodeException e) {
            log.error("❌ [Keycloak] Failed to set password: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Unable to set user password");
        }
    }

    public void assignRole(String userId, String roleName, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        try {
            // У тебе в Keycloak роль має назву "ROLE_ADMIN", тому шукаємо з цим префіксом
            String keycloakRoleName = "ROLE_" + roleName.toUpperCase(); // Наприклад, "ADMIN" → "ROLE_ADMIN"

            ResponseEntity<Map> roleResp = restTemplate.exchange(
                    keycloakUrl + "/admin/realms/" + realm + "/roles/" + keycloakRoleName,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    Map.class
            );

            Map<String, Object> role = roleResp.getBody();
            headers.setContentType(MediaType.APPLICATION_JSON);
            List<Map<String, Object>> roles = Collections.singletonList(role);

            restTemplate.postForEntity(
                    keycloakUrl + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/realm",
                    new HttpEntity<>(roles, headers),
                    Void.class
            );
        } catch (HttpStatusCodeException e) {
            log.error("❌ [Keycloak] Failed to assign role: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Unable to assign role to user");
        }
    }



    public String getTokenForUser(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=password"
                + "&client_id=" + clientId
                + "&username=" + username
                + "&password=" + password;

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token",
                    new HttpEntity<>(body, headers),
                    Map.class
            );
            return (String) response.getBody().get("access_token");
        } catch (HttpStatusCodeException e) {
            log.error("❌ [Keycloak] Failed to get token for user: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Unable to get token for newly created user");
        }
    }
}

