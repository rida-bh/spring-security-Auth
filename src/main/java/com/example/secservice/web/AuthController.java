package com.example.secservice.web;

import com.example.secservice.dto.AuthResponseDto;
import com.example.secservice.dto.LoginDTO;
import com.example.secservice.entities.AppUser;
import com.example.secservice.repositories.AppRoleRepositoriy;
import com.example.secservice.repositories.AppUserRepositoriy;
import com.example.secservice.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private AccountService accountService;

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody AppUser appUser){
        return accountService.register(appUser);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDTO loginDTO){
        return accountService.login(loginDTO);
    }
}
