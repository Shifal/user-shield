package com.shifal.userapi.repository;

import com.shifal.userapi.model.Role;
import com.shifal.userapi.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
