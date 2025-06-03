package com.john.company_records_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString(exclude = {"company", "service", "master"})
@Table(indexes = {
        @Index(name = "idx_record_date", columnList = "date"),
        @Index(name = "idx_record_company", columnList = "company_id")
})
public class Record {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int tip;
    private int earningsForMaster;

    @Enumerated(EnumType.STRING)
    private RecordStatus status;
}