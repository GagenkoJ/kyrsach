package com.john.company_records_app.dto;

import com.john.company_records_app.entity.RecordStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordResponseDto {
    private Long id;
    private String serviceName;
    private String masterName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int tip;
    private int earningsForMaster;
    private RecordStatus status;
}
