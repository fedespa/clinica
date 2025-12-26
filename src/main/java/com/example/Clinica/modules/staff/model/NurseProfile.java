package com.example.Clinica.modules.staff.model;

import com.example.Clinica.security.user.model.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "nurse_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NurseProfile {

    @Id
    private Long id;

    private LocalTime workStartTime;
    private LocalTime workEndTime;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(
            name = "nurse_working_days",
            joinColumns = @JoinColumn(name = "nurse_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Set<DayOfWeek> workingDays;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "id")
    private AppUser user;

}
