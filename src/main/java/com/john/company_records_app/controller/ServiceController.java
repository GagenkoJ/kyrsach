package com.john.company_records_app.controller;

import com.john.company_records_app.dto.ServiceRequestDto;
import com.john.company_records_app.dto.ServiceResponseDto;
import com.john.company_records_app.mapper.ServiceMapper;
import com.john.company_records_app.service.ServiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;
    private final ServiceMapper mapper;

    @PostMapping
    public ResponseEntity<ServiceResponseDto> create(@Valid @RequestBody ServiceRequestDto dto) {
        log.info("Received request to create service: {}", dto.getName());
        try {
            var created = serviceService.create(dto);
            var response = mapper.toDto(created);
            log.info("Service created with id: {}", response.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error while creating service: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/company/{companyId}")
    public List<ServiceResponseDto> getAllByCompany(@PathVariable Long companyId) {
        log.info("Fetching all services for companyId: {}", companyId);
        return serviceService.getAllByCompany(companyId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ServiceResponseDto getOne(@PathVariable Long id) {
        log.info("Fetching service with id: {}", id);
        return mapper.toDto(serviceService.getOne(id));
    }

    @PutMapping("/{id}")
    public ServiceResponseDto update(@PathVariable Long id, @Valid @RequestBody ServiceRequestDto dto) {
        log.info("Updating service with id: {}", id);
        var updated = mapper.toDto(serviceService.update(id, dto));
        log.info("Updated service with id: {}", updated.getId());
        return updated;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting service with id: {}", id);
        serviceService.delete(id);
        log.info("Deleted service with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}

