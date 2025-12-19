package com.example.Clinica.modules.patient.model;

import com.example.Clinica.common.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Patient extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String dni;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    private LocalDate birthDate;

    private String phoneNumber;

}
