package com.example.Clinica.modules.staff.dto;

import com.example.Clinica.modules.staff.model.DoctorSpecialty;
import com.example.Clinica.modules.staff.model.DoctorSpecialtyEnum;
import com.example.Clinica.security.user.model.AppUser;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

public record DoctorResponse(
        Long id,
        String email,
        String fullName,
        Set<DoctorSpecialtyEnum> specialties,
        LocalTime workStartTime,
        LocalTime workEndTime
) {

    public static DoctorResponse fromEntity(AppUser user) {
        return new DoctorResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName() + " " + user.getLastName(),
                user.getDoctorProfile().getSpecialties().stream()
                        .map(DoctorSpecialty::getName).collect(Collectors.toSet()),
                user.getDoctorProfile().getWorkStartTime(),
                user.getDoctorProfile().getWorkEndTime()
        );
    }

}
