package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.Role;
import com.example.simpleprojectungram.model.Users;
import com.example.simpleprojectungram.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MongoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userRepository.findByUsername(username);
        Users user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Set<Role> role = user.getRole();

        String[] strings = role.stream().map(r -> r.name()).toArray(String[]::new);
        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(strings)
                .build();

        return userDetails;
    }
}
