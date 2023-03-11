package com.example.secservice.security.repositories;

import com.example.secservice.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepositoriy extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
