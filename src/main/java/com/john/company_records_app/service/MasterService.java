package com.john.company_records_app.service;


import com.john.company_records_app.dto.MasterRequestDto;
import com.john.company_records_app.mapper.MasterMapper;
import com.john.company_records_app.entity.Admin;
import com.john.company_records_app.entity.Company;
import com.john.company_records_app.entity.Master;
import com.john.company_records_app.repository.AdminRepository;
import com.john.company_records_app.repository.CompanyRepository;
import com.john.company_records_app.repository.MasterRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MasterService {

    private final MasterRepository masterRepository;
    private final CompanyRepository companyRepository;
    private final MasterMapper masterMapper;

    public Master createMaster(MasterRequestDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + dto.getCompanyId()));
        Master master = masterMapper.toEntity(dto);
        master.setCompany(company);
        return masterRepository.save(master);
    }

    public List<Master> getMastersByCompany(Long companyId) {
        return masterRepository.findByCompanyId(companyId);
    }

    public Master getMaster(Long id) {
        return masterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Master not found with id: " + id));
    }

    public Master updateMaster(Long id, MasterRequestDto dto) {
        Master master = getMaster(id);
        masterMapper.updateEntityFromDto(master, dto);
        return masterRepository.save(master);
    }

    public void deleteMaster(Long id) {
        masterRepository.deleteById(id);
    }
}
