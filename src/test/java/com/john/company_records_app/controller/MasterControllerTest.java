package com.john.company_records_app.controller;

import com.john.company_records_app.mapper.MasterMapper;
import com.john.company_records_app.service.MasterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MasterController.class)
class MasterControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private MasterService service;
    @MockBean private MasterMapper mapper;

    @Test
    void shouldReturnMastersForCompany() throws Exception {
        given(service.getMastersByCompany(1L)).willReturn(List.of());

        mockMvc.perform(get("/api/masters/company/1"))
                .andExpect(status().isOk());
    }
}


