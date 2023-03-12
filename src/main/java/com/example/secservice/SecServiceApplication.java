package com.example.secservice;

import com.example.secservice.entities.AppRole;
import com.example.secservice.entities.AppUser;
import com.example.secservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
            accountService.addNewRole(new AppRole(null,"USER"));
            accountService.addNewRole(new AppRole(null,"CUSTOMER_MANAGER"));
            accountService.addNewRole(new AppRole(null,"ADMIN"));
            accountService.addNewRole(new AppRole(null,"PRODUCT_MANAGER"));
            accountService.addNewRole(new AppRole(null,"HR_MANAGER"));
/*            accountService.addNewUser(new AppUser(null,"user0","123456",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user1","123456",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user2","123456",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user3","123456",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"admin","123456",new ArrayList<>()));
            accountService.addRoleToUser("user0","CUSTOMER_MANAGER");
            accountService.addRoleToUser("user0","USER");
            accountService.addRoleToUser("user1","PRODUCT_MANAGER");
            accountService.addRoleToUser("user2","HR_MANAGER");
            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("admin","USER");*/
        };
    }
}
