package com.example.secservice.security;

import com.example.secservice.entities.AppRole;
import com.example.secservice.entities.AppUser;
import com.example.secservice.repositories.AppUserRepositoriy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private AppUserRepositoriy appUserRepositoriy;

    public CustomUserDetailsService(AppUserRepositoriy appUserRepositoriy) {
        this.appUserRepositoriy = appUserRepositoriy;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepositoriy.findByUsername(username);
        return new User(user.getUsername(),user.getPassword(),mapRoletoAuthorities(user.getAppRoles()));
    }
    private Collection<GrantedAuthority> mapRoletoAuthorities(Collection<AppRole> appRoles){
        Collection<GrantedAuthority> authorities= new ArrayList<>();
        appRoles.forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return authorities;
    }
}
