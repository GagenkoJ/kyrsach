package com.john.company_records_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"master", "company"})
public class MasterDailyStats {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private LocalDate date;

    private int totalClients;         // Кількість клієнтів
    private int totalWorkedMinutes;   // Загальний час роботи в хвилинах
    private int totalTips;            // Сума чайових
    private int totalEarnings;        // Заробіток майстра (без чайових або з ними — як вирішиш)
}
