package com.example.Clinica.modules.staff.dto;

import jakarta.validation.constraints.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public record CreateDoctorRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(max = 50) String firstName,
        @NotBlank @Size(max = 50) String lastName,
        @NotBlank @Size(max = 50) String password,
        @NotEmpty Set<Long> specialtyIds,
        @Min(0) @Max(60) Integer yearsOfExperience,
        @NotNull LocalTime workStartTime,
        @NotNull LocalTime workEndTime,
        @NotEmpty @Size(max = 7) Set<DayOfWeek> workingDays
) {
}
