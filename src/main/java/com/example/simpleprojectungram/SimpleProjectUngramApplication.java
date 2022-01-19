package com.example.simpleprojectungram;

import com.example.simpleprojectungram.model.Role;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SimpleProjectUngramApplication /*implements CommandLineRunner*/ {

    public static void main(String[] args) {
        SpringApplication.run(SimpleProjectUngramApplication.class, args);
    }

   /* @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@ungram.ru");
        user.setRole(Set.of(Role.ROLE_ADMIN, Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(user);
    }*/
}
