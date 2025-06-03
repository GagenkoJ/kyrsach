package com.john.company_records_app.service;

import com.john.company_records_app.dto.RecordRequestDto;
import com.john.company_records_app.entity.Company;
import com.john.company_records_app.entity.Master;
import com.john.company_records_app.entity.Record;
import com.john.company_records_app.entity.RecordStatus;
import com.john.company_records_app.entity.Service;
import com.john.company_records_app.mapper.RecordMapper;
import com.john.company_records_app.repository.CompanyRepository;
import com.john.company_records_app.repository.MasterRepository;
import com.john.company_records_app.repository.RecordRepository;
import com.john.company_records_app.repository.ServiceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final CompanyRepository companyRepository;
    private final ServiceRepository serviceRepository;
    private final MasterRepository masterRepository;
    private final RecordMapper mapper;

    public Record create(RecordRequestDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Service service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));
        Master master = masterRepository.findById(dto.getMasterId())
                .orElseThrow(() -> new EntityNotFoundException("Master not found"));

        return recordRepository.save(mapper.toEntity(dto, company, service, master));
    }

    public List<Record> getAllByCompany(Long companyId) {
        return recordRepository.findByCompanyId(companyId);
    }

    public Record getOne(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Record not found"));
    }

    public Record update(Long id, RecordRequestDto dto) {
        Record record = getOne(id);
        Service service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));
        Master master = masterRepository.findById(dto.getMasterId())
                .orElseThrow(() -> new EntityNotFoundException("Master not found"));

        mapper.update(record, dto, service, master);
        return recordRepository.save(record);
    }

    public void delete(Long id) {
        recordRepository.deleteById(id);
    }

    public Record updateStatus(Long id, RecordStatus status) {
        Record record = getOne(id);
        record.setStatus(status);
        return recordRepository.save(record);
    }
}
