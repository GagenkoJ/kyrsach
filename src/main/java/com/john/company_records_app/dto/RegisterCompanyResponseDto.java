package com.john.company_records_app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterCompanyResponseDto {
    private Long companyId;
    private Long adminId;
    private String name;
    private String email;
}

