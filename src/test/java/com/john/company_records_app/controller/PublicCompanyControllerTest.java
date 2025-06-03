package com.john.company_records_app.controller;

import com.john.company_records_app.dto.CompanyResponseDto;
import com.john.company_records_app.dto.RegisterCompanyRequestDto;
import com.john.company_records_app.dto.RegisterCompanyResponseDto;
import com.john.company_records_app.service.RegisterCompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublicCompanyController.class)
class PublicCompanyControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private RegisterCompanyService service;

    @Test
    void shouldReturnAllCompanies() throws Exception {
        given(service.getAllCompanies()).willReturn(List.of());

        mockMvc.perform(get("/api/public/companies"))
                .andExpect(status().isOk());
    }
}


