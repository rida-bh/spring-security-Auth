package com.example.secservice.security.repositories;

import com.example.secservice.security.entities.AppRole;
import com.example.secservice.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepositoriy extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
