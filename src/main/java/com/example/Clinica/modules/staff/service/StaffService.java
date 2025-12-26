package com.example.Clinica.modules.staff.service;

import com.example.Clinica.common.errors.ResourceNotFoundException;
import com.example.Clinica.common.errors.ScheduleInvalidException;
import com.example.Clinica.modules.staff.dto.CreateDoctorRequest;
import com.example.Clinica.modules.staff.dto.CreateNurseRequest;
import com.example.Clinica.modules.staff.dto.DoctorResponse;
import com.example.Clinica.modules.staff.dto.NurseResponse;
import com.example.Clinica.modules.staff.model.DoctorProfile;
import com.example.Clinica.modules.staff.model.DoctorSpecialty;
import com.example.Clinica.modules.staff.model.NurseProfile;
import com.example.Clinica.modules.staff.repository.DoctorSpecialtyRepository;
import com.example.Clinica.security.user.model.AppUser;
import com.example.Clinica.security.user.model.Role;
import com.example.Clinica.security.user.model.RoleName;
import com.example.Clinica.security.user.repository.RoleRepository;
import com.example.Clinica.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DoctorSpecialtyRepository doctorSpecialtyRepository;

    @Transactional
    public DoctorResponse createDoctor(CreateDoctorRequest dto) {

        Optional<AppUser> user = this.userRepository.findByEmail(dto.email());

        if (user.isPresent()) {
            throw new UsernameNotFoundException("El doctor ya existe. ");
        }

        validateWorkSchedule(dto.workStartTime(), dto.workEndTime());

        Set<DoctorSpecialty> specialties = new HashSet<>(this.doctorSpecialtyRepository.findByIdIn(dto.specialtyIds()));

        if (specialties.size() != dto.specialtyIds().size()) {
            throw new ResourceNotFoundException("Error: Una o más especialidades no existen. ");
        }

        Role role = this.roleRepository.findRoleByName(RoleName.ROLE_DOCTOR)
                .orElseThrow(() -> new ResourceNotFoundException("Error: El role no existe. "));

        AppUser appUser = AppUser.builder()
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .password(this.passwordEncoder.encode(dto.password()))
                .roles(Set.of(role))
                .isEnabled(true) // agregar permisos
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialsNoExpired(true)
                .build();

        DoctorProfile doctorProfile = DoctorProfile.builder()
                .user(appUser)
                .specialties(specialties)
                .workingDays(dto.workingDays())
                .yearsOfExperience(dto.yearsOfExperience())
                .workStartTime(dto.workStartTime())
                .workEndTime(dto.workEndTime())
                .build();

        appUser.setDoctorProfile(doctorProfile);

        this.userRepository.save(appUser);

        return DoctorResponse.fromEntity(appUser);
    }

    @Transactional
    public NurseResponse createNurse(CreateNurseRequest dto) {

        Optional<AppUser> user = this.userRepository.findByEmail(dto.email());

        if (user.isPresent()) {
            throw new UsernameNotFoundException("El enfermero ya existe. ");
        }

        validateWorkSchedule(dto.workStartTime(), dto.workEndTime());

        Role nurseRole = this.roleRepository.findRoleByName(RoleName.ROLE_NURSE)
                .orElseThrow(() -> new ResourceNotFoundException("Error: El role no existe. "));

        AppUser appUser = AppUser.builder()
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .password(this.passwordEncoder.encode(dto.password()))
                .roles(Set.of(nurseRole))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialsNoExpired(true)
                .build();

        NurseProfile nurseProfile = NurseProfile.builder()
                .user(appUser)
                .workStartTime(dto.workStartTime())
                .workEndTime(dto.workEndTime())
                .workingDays(dto.workingDays())
                .build();

        appUser.setNurseProfile(nurseProfile);

        this.userRepository.save(appUser);
        return NurseResponse.fromEntity(appUser);
    }

    private void validateWorkSchedule(LocalTime start, LocalTime end) {
        if (start.equals(end)) {
            throw new ScheduleInvalidException("El horario de inicio no puede ser igual al de fin. ");
        }
    }

}
