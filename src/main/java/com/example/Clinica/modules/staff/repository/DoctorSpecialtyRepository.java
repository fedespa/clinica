package com.example.Clinica.modules.staff.repository;

import com.example.Clinica.modules.staff.model.DoctorSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DoctorSpecialtyRepository extends JpaRepository<DoctorSpecialty,Long> {

    List<DoctorSpecialty> findByIdIn(Set<Long> specialtyIds);

}
