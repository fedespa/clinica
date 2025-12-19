package com.example.Clinica.modules.admission.model;

import com.example.Clinica.common.audit.AuditableEntity;
import com.example.Clinica.security.user.AppUser;
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
public class TriageLog extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admission admission;

    @Enumerated(EnumType.STRING)
    private TriageLevel previousLevel;

    @Enumerated(EnumType.STRING)
    private TriageLevel newLevel;

    private String changeReason;
    private LocalDateTime changeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_By")
    private AppUser user;

}
