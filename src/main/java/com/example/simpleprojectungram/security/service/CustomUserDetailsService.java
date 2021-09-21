package com.example.simpleprojectungram.security.service;

import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.security.CustomUserDetails;
import com.example.simpleprojectungram.service.UserService;
import com.example.simpleprojectungram.service.impl.UserServiceImpl;
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
        User users = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return CustomUserDetails.fromUserEntityToCustomUserDetails(users);
    }
}
