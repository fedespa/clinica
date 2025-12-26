package com.example.Clinica.modules.staff.dto;


import com.example.Clinica.security.user.model.AppUser;

import java.time.LocalTime;

public record NurseResponse(
        Long id,
        String email,
        String fullName,
        LocalTime workStartTime,
        LocalTime workEndTime
) {

    public static NurseResponse fromEntity(AppUser user){
        return new NurseResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName() + " " + user.getLastName(),
                user.getNurseProfile().getWorkStartTime(),
                user.getNurseProfile().getWorkEndTime()
        );
    }

}
