package com.john.company_records_app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordRequestDto {
    @NotNull
    private Long companyId;
    @NotNull private Long serviceId;
    @NotNull private Long masterId;

    @NotNull private LocalDate date;
    @NotNull private LocalTime startTime;
    @NotNull private LocalTime endTime;

    private int tip;
    private int earningsForMaster;
}
