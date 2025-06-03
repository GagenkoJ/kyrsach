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
@ToString(exclude = "company")
public class CompanyDailyStats {
        @Id
        @GeneratedValue
        private Long id;

        @ManyToOne
        @JoinColumn(name = "company_id")
        private Company company;

        private LocalDate date;

        private int totalClients;              // Загальна кількість записів
        private int completedClients;          // Кількість завершених (DONE)
        private int totalIncomeFromClients;    // Загальний дохід (включно з чайовими)
        private int totalPaidToMasters;        // Сума виплат майстрам
        private int totalTips;                 // Загальна сума чайових
        private int profit;                    // Дохід - виплати майстрам
}

