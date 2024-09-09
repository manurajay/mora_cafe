package com.mora.cafe.com.mora.cafe.repo;

import com.mora.cafe.com.mora.cafe.POJO.ERole;
import com.mora.cafe.com.mora.cafe.POJO.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
