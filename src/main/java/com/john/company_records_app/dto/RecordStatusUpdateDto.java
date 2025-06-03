package com.john.company_records_app.dto;

import com.john.company_records_app.entity.RecordStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordStatusUpdateDto {
    @NotNull
    private RecordStatus status;
}
