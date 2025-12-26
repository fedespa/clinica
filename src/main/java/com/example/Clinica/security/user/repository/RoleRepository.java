package com.example.Clinica.security.user.repository;

import com.example.Clinica.security.user.model.Role;
import com.example.Clinica.security.user.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    List<Role> findRolesByNameIn(List<String> roleNames);
    Optional<Role> findRoleByName(RoleName name);

}
