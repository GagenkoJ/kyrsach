package com.john.company_records_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequestDto {
    @NotNull
    private Long companyId;

    @NotBlank
    private String type;

    @NotBlank
    private String name;

    @Min(1)
    private int durationMinutes;

    @Min(0)
    private int price;
}

