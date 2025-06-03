package com.john.company_records_app.repository;

import com.john.company_records_app.entity.CompanyDailyStats;
import com.john.company_records_app.entity.MasterDailyStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MasterDailyStatsRepository extends JpaRepository<MasterDailyStats, Long> {
    //List<MasterDailyStats> findByMasterId(Long masterId);
    //MasterDailyStats findByMasterIdAndDate(Long masterId, LocalDate date);
    //List<CompanyDailyStats> findByCompanyIdAndDateBetween(Long companyId, LocalDate start, LocalDate end);
    //List<MasterDailyStats> findByMasterIdAndDateBetween(Long masterId, LocalDate start, LocalDate end);
    List<MasterDailyStats> findByMasterIdAndDateBetween(Long masterId, LocalDate start, LocalDate end);
}