package com.example.Clinica.modules.admission.model;

import com.example.Clinica.common.audit.TraceableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "triage_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TriageLog extends TraceableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admission_id")
    private Admission admission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TriageLevel previousLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TriageLevel newLevel;

    private String changeReason;

    @Column(nullable = false)
    private LocalDateTime changeDate;
}
