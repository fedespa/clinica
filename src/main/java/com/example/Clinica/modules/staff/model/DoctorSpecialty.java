package com.example.Clinica.modules.staff.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctor_specialties")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DoctorSpecialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private DoctorSpecialtyEnum name;

    @Column(length = 500)
    private String description;

}
