package com.example.Clinica.modules.admission.model;

import com.example.Clinica.modules.patient.model.Patient;
import com.example.Clinica.security.user.AppUser;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private TriageLevel triageLevel;

    @Enumerated(EnumType.STRING)
    private AdmissionStatus status;

    private String reasonForConsultation;

    private LocalDateTime entryDate;
    private LocalDateTime attentionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private AppUser attendingDoctor;

}
