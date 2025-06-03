package com.john.company_records_app.mapper;

import com.john.company_records_app.dto.CompanyResponseDto;
import com.john.company_records_app.dto.RegisterCompanyRequestDto;
import com.john.company_records_app.dto.RegisterCompanyResponseDto;
import com.john.company_records_app.entity.Admin;
import com.john.company_records_app.entity.Company;
import com.john.company_records_app.entity.UserRole;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RegisterCompanyMapper {

    private final PasswordEncoder passwordEncoder;

    public Company toEntity(RegisterCompanyRequestDto dto) {
        Company company = new Company();

        Admin admin = Admin.builder()
                .name(dto.getAdminName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(UserRole.ADMIN)
                .company(company)
                .build();

        company.setAdmin(admin);
        return company;
    }

    public RegisterCompanyResponseDto toDto(Company company) {
        Admin admin = company.getAdmin();
        return RegisterCompanyResponseDto.builder()
                .companyId(company.getId())
                .adminId(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .build();
    }

    public CompanyResponseDto toCompanyDto(Company company) {
        Admin admin = company.getAdmin();
        return CompanyResponseDto.builder()
                .id(company.getId())
                .adminName(admin.getName())
                .adminEmail(admin.getEmail())
                .build();
    }

}

