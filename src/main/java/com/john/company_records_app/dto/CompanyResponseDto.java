package com.john.company_records_app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponseDto {
    private Long id;
    private String adminName;
    private String adminEmail;
}

