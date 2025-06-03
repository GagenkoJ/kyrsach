package com.john.company_records_app.mapper;

import com.john.company_records_app.dto.ServiceRequestDto;
import com.john.company_records_app.dto.ServiceResponseDto;
import com.john.company_records_app.entity.Company;
import com.john.company_records_app.entity.Service;

import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public Service toEntity(ServiceRequestDto dto, Company company) {
        return Service.builder()
                .type(dto.getType())
                .name(dto.getName())
                .durationMinutes(dto.getDurationMinutes())
                .price(dto.getPrice())
                .company(company)
                .build();
    }

    public void updateEntity(Service service, ServiceRequestDto dto) {
        service.setType(dto.getType());
        service.setName(dto.getName());
        service.setDurationMinutes(dto.getDurationMinutes());
        service.setPrice(dto.getPrice());
    }

    public ServiceResponseDto toDto(Service service) {
        return ServiceResponseDto.builder()
                .id(service.getId())
                .type(service.getType())
                .name(service.getName())
                .durationMinutes(service.getDurationMinutes())
                .price(service.getPrice())
                .build();
    }
}
