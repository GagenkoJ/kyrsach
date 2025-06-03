package com.john.company_records_app.controller;

import com.john.company_records_app.dto.CompanyResponseDto;
import com.john.company_records_app.dto.RegisterCompanyRequestDto;
import com.john.company_records_app.dto.RegisterCompanyResponseDto;
import com.john.company_records_app.service.RegisterCompanyService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicCompanyController {

    private final RegisterCompanyService service;

    @PostMapping("/register-company")
    public ResponseEntity<RegisterCompanyResponseDto> registerCompany(
            @Valid @RequestBody RegisterCompanyRequestDto dto) {
        log.info("Registering new company: {}", dto.getCompanyName());
        RegisterCompanyResponseDto response = service.register(dto);
        log.info("Company registered successfully with ID: {}", response.getCompanyId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/companies")
    public List<CompanyResponseDto> getAllCompanies() {
        log.info("Fetching list of all companies");
        List<CompanyResponseDto> companies = service.getAllCompanies();
        log.info("Found {} companies", companies.size());
        return companies;
    }
}
