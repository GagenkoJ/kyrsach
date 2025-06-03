package com.john.company_records_app.controller;

import com.john.company_records_app.controller.DailyStatsController;
import com.john.company_records_app.entity.CompanyDailyStats;
import com.john.company_records_app.entity.MasterDailyStats;
import com.john.company_records_app.service.DailyStatsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DailyStatsController.class)
class DailyStatsControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private DailyStatsService service;

    @Test
    void shouldGenerateCompanyStats() throws Exception {
        mockMvc.perform(post("/api/stats/company/1/generate")
                        .param("date", "2025-06-01"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnCompanyStats() throws Exception {
        mockMvc.perform(get("/api/stats/company/1/stats")
                        .param("from", "2025-06-01")
                        .param("to", "2025-06-02"))
                .andExpect(status().isOk());
    }
}

