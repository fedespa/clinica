package com.example.Clinica.modules.staff.model;

import com.example.Clinica.security.user.model.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor_profiles")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter @Builder
public class DoctorProfile {

    @Id
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "doctor_specialty_map",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    @Builder.Default
    private Set<DoctorSpecialty> specialties = new HashSet<>();

    private Integer yearsOfExperience;

    private LocalTime workStartTime;
    private LocalTime workEndTime;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(
            name = "doctor_working_days",
            joinColumns = @JoinColumn(name = "doctor_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    @Builder.Default
    private Set<DayOfWeek> workingDays = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private AppUser user;

}
