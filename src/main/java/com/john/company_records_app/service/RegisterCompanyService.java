package com.john.company_records_app.service;


import com.john.company_records_app.dto.CompanyResponseDto;
import com.john.company_records_app.dto.RegisterCompanyRequestDto;
import com.john.company_records_app.dto.RegisterCompanyResponseDto;
import com.john.company_records_app.entity.Company;
import com.john.company_records_app.mapper.RegisterCompanyMapper;
import com.john.company_records_app.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RegisterCompanyService {

    private final CompanyRepository companyRepository;
    private final RegisterCompanyMapper mapper;

    @Transactional
    public RegisterCompanyResponseDto register(RegisterCompanyRequestDto dto) {
        Company company = mapper.toEntity(dto);
        Company saved = companyRepository.save(company);
        return mapper.toDto(saved);
    }

    public List<CompanyResponseDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(mapper::toCompanyDto)
                .toList();
    }

}
