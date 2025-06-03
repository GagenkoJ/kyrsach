package com.john.company_records_app.repository;

import com.john.company_records_app.entity.Record;
import com.john.company_records_app.entity.Company;
import com.john.company_records_app.entity.Master;
import com.john.company_records_app.entity.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    // Записи по компанії
    List<Record> findByCompany(Company company);

    // Записи по компанії та даті
    List<Record> findByCompanyAndDate(Company company, LocalDate date);

    // Записи по майстру та даті
    List<Record> findByMasterAndDate(Master master, LocalDate date);

    // Записи по даті
    List<Record> findByDate(LocalDate date);

    // За потреби можна додати пошук по статусу
    List<Record> findByCompanyAndDateAndStatus(Company company, LocalDate date, RecordStatus status);

    List<Record> findByCompanyId(Long companyId);

    List<Record> findByCompanyIdAndDate(Long companyId, LocalDate date);

    List<Record> findByMasterIdAndCompanyIdAndDate(Long masterId, Long companyId, LocalDate date);

}

