package com.john.company_records_app.dto;

import lombok.Data;

@Data
public class JwtAuthenticationDto {
    private String token;
    public String refreshToken;
}
