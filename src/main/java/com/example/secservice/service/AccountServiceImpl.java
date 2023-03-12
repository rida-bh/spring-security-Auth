package com.example.secservice.service;

import com.example.secservice.dto.AuthResponseDto;
import com.example.secservice.dto.LoginDTO;
import com.example.secservice.entities.AppRole;
import com.example.secservice.entities.AppUser;
import com.example.secservice.repositories.AppRoleRepositoriy;
import com.example.secservice.repositories.AppUserRepositoriy;
import com.example.secservice.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AuthenticationManager authenticationManager;
    private AppUserRepositoriy appUserRepositoriy;
    private AppRoleRepositoriy appRoleRepositoriy;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
/*    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepositoriy.save(appUser);
    }*/
    @Override
    public ResponseEntity<String> register(AppUser appUser) {
        if(appUserRepositoriy.existsByUsername(appUser.getUsername())){
            return new ResponseEntity<>("username Taken", HttpStatus.BAD_REQUEST);
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepositoriy.save(appUser);
        return new ResponseEntity<>("user register success", HttpStatus.OK);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepositoriy.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser =appUserRepositoriy.findByUsername(username);
        AppRole appRole =appRoleRepositoriy.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }



    @Override
    public List<AppUser> listUsers() {

        return appUserRepositoriy.findAll();
    }

    @Override
    public ResponseEntity<AuthResponseDto> login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }
}
