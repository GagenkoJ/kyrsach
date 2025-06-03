package com.john.company_records_app.controller;

import com.john.company_records_app.entity.CompanyDailyStats;
import com.john.company_records_app.entity.MasterDailyStats;
import com.john.company_records_app.service.DailyStatsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class DailyStatsController {

    private final DailyStatsService dailyStatsService;

    @PostMapping("/company/{id}/generate")
    public ResponseEntity<Void> generateCompanyStats(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Generating company stats for companyId={} on date={}", id, date);
        dailyStatsService.generateCompanyDailyStats(id, date);
        log.info("Company stats generated successfully for companyId={}", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/company/{id}/stats")
    public ResponseEntity<List<CompanyDailyStats>> getCompanyStats(
            @PathVariable Long id,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        log.info("Fetching company stats for companyId={} from {} to {}", id, from, to);
        List<CompanyDailyStats> stats = dailyStatsService.getCompanyStats(id, from, to);
        log.info("Fetched {} entries for companyId={}", stats.size(), id);
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/master/{id}/generate")
    public ResponseEntity<Void> generateMasterStats(
            @PathVariable Long id,
            @RequestParam Long companyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Generating master stats for masterId={} in companyId={} on date={}", id, companyId, date);
        dailyStatsService.generateMasterDailyStats(id, companyId, date);
        log.info("Master stats generated successfully for masterId={} in companyId={}", id, companyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/master/{id}/stats")
    public ResponseEntity<List<MasterDailyStats>> getMasterStats(
            @PathVariable Long id,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        log.info("Fetching master stats for masterId={} from {} to {}", id, from, to);
        List<MasterDailyStats> stats = dailyStatsService.getMasterStats(id, from, to);
        log.info("Fetched {} entries for masterId={}", stats.size(), id);
        return ResponseEntity.ok(stats);
    }
}
