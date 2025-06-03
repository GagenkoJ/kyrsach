package com.john.company_records_app.mapper;

import com.john.company_records_app.dto.MasterRequestDto;
import com.john.company_records_app.dto.MasterResponseDto;
import com.john.company_records_app.entity.Master;
import com.john.company_records_app.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MasterMapper {

    private final PasswordEncoder passwordEncoder;

    public Master toEntity(MasterRequestDto dto) {
        return Master.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(UserRole.MASTER)
                .build();
    }

    public MasterResponseDto toDto(Master master) {
        return MasterResponseDto.builder()
                .id(master.getId())
                .firstName(master.getFirstName())
                .lastName(master.getLastName())
                .email(master.getEmail())
                .role(master.getRole())
                .build();
    }

    public void updateEntityFromDto(Master master, MasterRequestDto dto) {
        master.setFirstName(dto.getFirstName());
        master.setLastName(dto.getLastName());
        master.setEmail(dto.getEmail());
        master.setPassword(passwordEncoder.encode(dto.getPassword()));
        master.setRole(UserRole.MASTER);
    }
}
