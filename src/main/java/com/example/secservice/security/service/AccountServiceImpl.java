package com.example.secservice.security.service;

import com.example.secservice.security.entities.AppRole;
import com.example.secservice.security.entities.AppUser;
import com.example.secservice.security.repositories.AppRoleRepositoriy;
import com.example.secservice.security.repositories.AppUserRepositoriy;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class AccountServiceImpl implements AccountService , UserDetailsService {
    private AppUserRepositoriy appUserRepositoriy;
    private AppRoleRepositoriy appRoleRepositoriy;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepositoriy.save(appUser);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepositoriy.findByUsername(username);

        if(appUser == null)
            throw new UsernameNotFoundException("User with username: " +username +" not found !");
        else {
            Collection<GrantedAuthority>authorities =new ArrayList<>();
            appUser.getAppRoles().forEach(role->{
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            });
            return new User(appUser.getUsername(),appUser.getPassword(),authorities);
        }

    }

    @Override
    public List<AppUser> listUsers() {

        return appUserRepositoriy.findAll();
    }
}
