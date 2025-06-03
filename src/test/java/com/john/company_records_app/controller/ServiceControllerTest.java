package com.john.company_records_app.controller;

import com.john.company_records_app.mapper.ServiceMapper;
import com.john.company_records_app.service.ServiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServiceController.class)
class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceService service;

    @MockBean
    private ServiceMapper mapper;

    @Test
    void shouldReturnServicesForCompany() throws Exception {
        given(service.getAllByCompany(1L)).willReturn(List.of());

        mockMvc.perform(get("/api/services/company/1"))
                .andExpect(status().isOk());
    }
}



