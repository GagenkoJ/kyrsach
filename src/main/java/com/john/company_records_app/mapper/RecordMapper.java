package com.john.company_records_app.mapper;


import com.john.company_records_app.dto.RecordRequestDto;
import com.john.company_records_app.dto.RecordResponseDto;
import com.john.company_records_app.entity.Company;
import com.john.company_records_app.entity.Master;
import com.john.company_records_app.entity.Record;
import com.john.company_records_app.entity.RecordStatus;
import com.john.company_records_app.entity.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RecordMapper {

    public Record toEntity(RecordRequestDto dto, Company company, Service service, Master master) {
        return Record.builder()
                .company(company)
                .service(service)
                .master(master)
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .tip(dto.getTip())
                .earningsForMaster(dto.getEarningsForMaster())
                .status(RecordStatus.EXPECTITION)
                .build();
    }

    public void update(Record record, RecordRequestDto dto, Service service, Master master) {
        record.setService(service);
        record.setMaster(master);
        record.setDate(dto.getDate());
        record.setStartTime(dto.getStartTime());
        record.setEndTime(dto.getEndTime());
        record.setTip(dto.getTip());
        record.setEarningsForMaster(dto.getEarningsForMaster());
    }

    public RecordResponseDto toDto(Record record) {
        return RecordResponseDto.builder()
                .id(record.getId())
                .serviceName(record.getService().getName())
                .masterName(record.getMaster().getFirstName() + " " + record.getMaster().getLastName())
                .date(record.getDate())
                .startTime(record.getStartTime())
                .endTime(record.getEndTime())
                .tip(record.getTip())
                .earningsForMaster(record.getEarningsForMaster())
                .status(record.getStatus())
                .build();
    }
}

