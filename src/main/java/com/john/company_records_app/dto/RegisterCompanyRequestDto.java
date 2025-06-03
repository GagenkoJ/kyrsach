package com.john.company_records_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterCompanyRequestDto {

    @NotBlank
    private String companyName; // опціонально, якщо компанія має ще поля

    @NotBlank
    private String adminName;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    @NotBlank
    private String password;
}

