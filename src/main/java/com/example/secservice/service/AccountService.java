package com.example.secservice.service;

import com.example.secservice.dto.AuthResponseDto;
import com.example.secservice.dto.LoginDTO;
import com.example.secservice.entities.AppRole;
import com.example.secservice.entities.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AccountService {
    //AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    ResponseEntity<String> register(AppUser appUser);
    List<AppUser> listUsers();

    ResponseEntity<AuthResponseDto> login(LoginDTO loginDTO);
}
