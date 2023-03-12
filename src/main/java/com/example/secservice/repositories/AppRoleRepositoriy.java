package com.example.secservice.repositories;

import com.example.secservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepositoriy extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
