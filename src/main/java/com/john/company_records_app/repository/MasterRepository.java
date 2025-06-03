package com.john.company_records_app.repository;

import com.john.company_records_app.entity.Master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
    List<Master> findByCompanyId(Long companyId);
    Master findByEmail(String email);


}