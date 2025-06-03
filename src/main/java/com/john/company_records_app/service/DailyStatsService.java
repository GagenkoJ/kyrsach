package com.john.company_records_app.service;

import com.john.company_records_app.entity.*;
import com.john.company_records_app.entity.Record;
import com.john.company_records_app.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyStatsService {
    private final RecordRepository recordRepository;
    private final CompanyRepository companyRepository;
    private final MasterRepository masterRepository;
    private final CompanyDailyStatsRepository companyDailyStatsRepository;
    private final MasterDailyStatsRepository masterDailyStatsRepository;

    @Transactional
    public void generateCompanyDailyStats(Long companyId, LocalDate date) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        List<Record> records = recordRepository.findByCompanyIdAndDate(companyId, date);

        int totalClients = records.size();
        int completedClients = (int) records.stream().filter(r -> r.getStatus() == RecordStatus.DONE).count();
        int totalIncome = records.stream().mapToInt(r -> r.getService().getPrice() + r.getTip()).sum();
        int totalTips = records.stream().mapToInt(Record::getTip).sum();
        int totalPaid = records.stream().mapToInt(Record::getEarningsForMaster).sum();
        int profit = totalIncome - totalPaid;

        CompanyDailyStats stats = CompanyDailyStats.builder()
                .company(company)
                .date(date)
                .totalClients(totalClients)
                .completedClients(completedClients)
                .totalIncomeFromClients(totalIncome)
                .totalTips(totalTips)
                .totalPaidToMasters(totalPaid)
                .profit(profit)
                .build();

        companyDailyStatsRepository.save(stats);
    }

    @Transactional
    public void generateMasterDailyStats(Long masterId, Long companyId, LocalDate date) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new EntityNotFoundException("Master not found"));

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        List<Record> records = recordRepository.findByMasterIdAndCompanyIdAndDate(masterId, companyId, date);

        int totalClients = records.size();
        int totalTips = records.stream().mapToInt(Record::getTip).sum();
        int totalEarnings = records.stream().mapToInt(Record::getEarningsForMaster).sum();
        int totalMinutes = records.stream()
                .mapToInt(r -> (int) Duration.between(r.getStartTime(), r.getEndTime()).toMinutes())
                .sum();

        MasterDailyStats stats = MasterDailyStats.builder()
                .master(master)
                .company(company)
                .date(date)
                .totalClients(totalClients)
                .totalTips(totalTips)
                .totalEarnings(totalEarnings)
                .totalWorkedMinutes(totalMinutes)
                .build();

        masterDailyStatsRepository.save(stats);
    }

    public List<CompanyDailyStats> getCompanyStats(Long companyId, LocalDate from, LocalDate to) {
        return companyDailyStatsRepository.findByCompanyIdAndDateBetween(companyId, from, to);
    }

    public List<MasterDailyStats> getMasterStats(Long masterId, LocalDate from, LocalDate to) {
        return masterDailyStatsRepository.findByMasterIdAndDateBetween(masterId, from, to);
    }
}