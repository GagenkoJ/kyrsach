package com.john.company_records_app.controller;

import com.john.company_records_app.controller.RecordController;
import com.john.company_records_app.dto.RecordRequestDto;
import com.john.company_records_app.dto.RecordResponseDto;
import com.john.company_records_app.dto.RecordStatusUpdateDto;
import com.john.company_records_app.mapper.RecordMapper;
import com.john.company_records_app.service.RecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(RecordController.class)
class RecordControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private RecordService service;
    @MockBean private RecordMapper mapper;

    @Test
    @WithMockUser(username = "test", roles = {"USER"}) // ← додано
    void shouldReturnRecordsForCompany() throws Exception {
        Long companyId = 1L;
        given(service.getAllByCompany(companyId)).willReturn(List.of());
        mockMvc.perform(get("/api/records/company/" + companyId))
                .andExpect(status().isOk());
    }
}


