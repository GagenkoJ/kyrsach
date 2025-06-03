package com.john.company_records_app.controller;

import com.john.company_records_app.dto.MasterRequestDto;
import com.john.company_records_app.dto.MasterResponseDto;
import com.john.company_records_app.mapper.MasterMapper;
import com.john.company_records_app.entity.Master;
import com.john.company_records_app.service.MasterService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/masters")
public class MasterController {

    private final MasterService masterService;
    private final MasterMapper masterMapper;

    @PostMapping
    public ResponseEntity<MasterResponseDto> create(@Valid @RequestBody MasterRequestDto dto) {
        log.info("Creating master with email: {}", dto.getEmail());
        Master master = masterService.createMaster(dto);
        log.info("Master created with ID: {}", master.getId());
        return new ResponseEntity<>(masterMapper.toDto(master), HttpStatus.CREATED);
    }

    @GetMapping("/company/{companyId}")
    public List<MasterResponseDto> getAll(@PathVariable Long companyId) {
        log.info("Getting all masters for company ID: {}", companyId);
        List<MasterResponseDto> result = masterService.getMastersByCompany(companyId)
                .stream()
                .map(masterMapper::toDto)
                .toList();
        log.info("Found {} masters for company ID: {}", result.size(), companyId);
        return result;
    }

    @GetMapping("/{id}")
    public MasterResponseDto getOne(@PathVariable Long id) {
        log.info("Fetching master by ID: {}", id);
        return masterMapper.toDto(masterService.getMaster(id));
    }

    @PutMapping("/{id}")
    public MasterResponseDto update(@PathVariable Long id, @Valid @RequestBody MasterRequestDto dto) {
        log.info("Updating master ID: {}", id);
        return masterMapper.toDto(masterService.updateMaster(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting master ID: {}", id);
        masterService.deleteMaster(id);
        log.info("Master with ID {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}

