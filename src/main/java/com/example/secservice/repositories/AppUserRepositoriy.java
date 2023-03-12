package com.example.secservice.repositories;

import com.example.secservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepositoriy extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
    Boolean existsByUsername(String username);
}
