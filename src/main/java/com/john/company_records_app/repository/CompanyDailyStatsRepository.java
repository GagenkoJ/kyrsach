package com.john.company_records_app.repository;

import com.john.company_records_app.entity.CompanyDailyStats;
import com.john.company_records_app.entity.MasterDailyStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompanyDailyStatsRepository extends JpaRepository<CompanyDailyStats, Long> {
    //List<CompanyDailyStats> findByCompanyId(Long companyId);
    //CompanyDailyStats findByCompanyIdAndDate(Long companyId, LocalDate date);
    List<CompanyDailyStats> findByCompanyIdAndDateBetween(Long companyId, LocalDate start, LocalDate end);
    //List<MasterDailyStats> findByMasterIdAndDateBetween(Long masterId, LocalDate start, LocalDate end);

}
