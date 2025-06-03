package com.john.company_records_app.service;

import com.john.company_records_app.dto.ServiceRequestDto;
import com.john.company_records_app.entity.Company;
import com.john.company_records_app.entity.Service;
import com.john.company_records_app.mapper.ServiceMapper;
import com.john.company_records_app.repository.CompanyRepository;
import com.john.company_records_app.repository.ServiceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CompanyRepository companyRepository;
    private final ServiceMapper mapper;

    public Service create(ServiceRequestDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Service service = mapper.toEntity(dto, company);
        return serviceRepository.save(service);
    }

    public List<Service> getAllByCompany(Long companyId) {
        return serviceRepository.findByCompanyId(companyId);
    }

    public Service getOne(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));
    }

    public Service update(Long id, ServiceRequestDto dto) {
        Service service = getOne(id);
        mapper.updateEntity(service, dto);
        return serviceRepository.save(service);
    }

    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }
}

