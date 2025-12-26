package com.example.Clinica.modules.staff.controller;

import com.example.Clinica.modules.staff.dto.CreateDoctorRequest;
import com.example.Clinica.modules.staff.dto.CreateNurseRequest;
import com.example.Clinica.modules.staff.dto.DoctorResponse;
import com.example.Clinica.modules.staff.dto.NurseResponse;
import com.example.Clinica.modules.staff.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/doctors")
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody @Valid CreateDoctorRequest dto) {

        DoctorResponse response = this.staffService.createDoctor(dto);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/nurses")
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ResponseEntity<NurseResponse> createNurse(@RequestBody @Valid CreateNurseRequest dto) {

        NurseResponse response = this.staffService.createNurse(dto);

        return ResponseEntity.ok().body(response);
    }


}
