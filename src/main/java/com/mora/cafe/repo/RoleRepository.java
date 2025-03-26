package com.mora.cafe.repo;

import com.mora.cafe.pojo.ERole;
import com.mora.cafe.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
