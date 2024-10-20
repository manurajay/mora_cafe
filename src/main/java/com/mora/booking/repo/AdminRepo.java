package com.mora.booking.repo;

import com.mora.booking.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Admin findAdminById(Long id);
}
