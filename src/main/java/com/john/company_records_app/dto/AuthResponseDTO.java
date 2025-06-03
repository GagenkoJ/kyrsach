package com.john.company_records_app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private Long adminId;
    private String email;
    private String name;
    private Long companyId;
    private String token;
}
