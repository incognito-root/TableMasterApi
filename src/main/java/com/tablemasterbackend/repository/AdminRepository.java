package com.tablemasterbackend.repository;

import com.tablemasterbackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByAdminUsername(String username);
}
