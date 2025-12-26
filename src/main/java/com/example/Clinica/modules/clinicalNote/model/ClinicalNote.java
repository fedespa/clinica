package com.example.Clinica.modules.clinicalNote.model;

import com.example.Clinica.common.audit.FullAuditableEntity;
import com.example.Clinica.modules.admission.model.Admission;
import com.example.Clinica.modules.patient.model.Patient;
import com.example.Clinica.security.user.model.AppUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clinical_notes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClinicalNote extends FullAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admission_id", nullable = false)
    private Admission admission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id", nullable = false)
    private AppUser professional;

    @Enumerated(EnumType.STRING)
    @Column(name = "note_type", nullable = false)
    private ClinicalNoteType noteType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
