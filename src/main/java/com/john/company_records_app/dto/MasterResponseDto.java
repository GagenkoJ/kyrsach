package com.john.company_records_app.dto;

import com.john.company_records_app.entity.UserRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
}
