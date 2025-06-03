package com.john.company_records_app.controller;

import com.john.company_records_app.dto.RecordRequestDto;
import com.john.company_records_app.dto.RecordResponseDto;
import com.john.company_records_app.dto.RecordStatusUpdateDto;
import com.john.company_records_app.mapper.RecordMapper;
import com.john.company_records_app.service.RecordService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final RecordMapper mapper;

    @PostMapping
    public ResponseEntity<RecordResponseDto> create(@Valid @RequestBody RecordRequestDto dto) {
        return new ResponseEntity<>(mapper.toDto(recordService.create(dto)), HttpStatus.CREATED);
    }

    @GetMapping("/company/{companyId}")
    public List<RecordResponseDto> getAll(@PathVariable Long companyId) {
        return recordService.getAllByCompany(companyId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public RecordResponseDto getOne(@PathVariable Long id) {
        return mapper.toDto(recordService.getOne(id));
    }

    @PutMapping("/{id}")
    public RecordResponseDto update(@PathVariable Long id, @Valid @RequestBody RecordRequestDto dto) {
        return mapper.toDto(recordService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recordService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public RecordResponseDto updateStatus(@PathVariable Long id, @Valid @RequestBody RecordStatusUpdateDto dto) {
        return mapper.toDto(recordService.updateStatus(id, dto.getStatus()));
    }
}
