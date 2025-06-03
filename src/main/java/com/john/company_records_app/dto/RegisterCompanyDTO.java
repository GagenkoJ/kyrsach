package com.john.company_records_app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCompanyDTO {
    private String companyName;
    private String email;
    private String password;
    private String surname;
    private String name;
}
