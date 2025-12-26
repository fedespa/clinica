package com.example.Clinica.modules.staff.dto;

import jakarta.validation.constraints.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public record CreateNurseRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(max = 50) String firstName,
        @NotBlank @Size(max = 50) String lastName,
        @NotBlank @Size(max = 50) String password,
        @NotNull LocalTime workStartTime,
        @NotNull LocalTime workEndTime,
        @NotEmpty @Size(max = 7) Set<DayOfWeek> workingDays
) {
}
