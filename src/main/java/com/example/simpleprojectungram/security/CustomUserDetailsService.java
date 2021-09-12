package com.example.simpleprojectungram.security;

import com.example.simpleprojectungram.model.Users;
import com.example.simpleprojectungram.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return CustomUserDetails.fromUserEntityToCustomUserDetails(users);
    }
}
