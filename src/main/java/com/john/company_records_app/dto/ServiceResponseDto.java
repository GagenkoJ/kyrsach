package com.john.company_records_app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResponseDto {
    private Long id;
    private String type;
    private String name;
    private int durationMinutes;
    private int price;
}


