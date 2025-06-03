package com.john.company_records_app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDTO {
    private Long adminId;
    private String email;
    private String name;
    private Long companyId;
}
