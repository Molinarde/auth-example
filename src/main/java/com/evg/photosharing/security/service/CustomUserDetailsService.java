package com.evg.photosharing.security.service;

import com.evg.photosharing.model.User;
import com.evg.photosharing.security.CustomUserDetails;
import com.evg.photosharing.service.UserService;
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
