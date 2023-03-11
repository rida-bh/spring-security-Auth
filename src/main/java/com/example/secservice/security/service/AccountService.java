package com.example.secservice.security.service;

import com.example.secservice.security.entities.AppRole;
import com.example.secservice.security.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    UserDetails loadUserByUsername(String username);
    List<AppUser> listUsers();
}
