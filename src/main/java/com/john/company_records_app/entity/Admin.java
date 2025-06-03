package com.john.company_records_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString(exclude = "company")
public class Admin {
    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "company_id", unique = true)
    private Company company;

    private String name; // 🔄 Виправлено Name → name
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}

