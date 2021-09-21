package com.example.simpleprojectungram.security;

import com.example.simpleprojectungram.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public CustomUserDetails(String id, String username, String email, String password, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User usersEntity) {
        List<SimpleGrantedAuthority> collect = usersEntity.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
        return new CustomUserDetails(usersEntity.getId(), usersEntity.getUsername(), usersEntity.getEmail(), usersEntity.getPassword(), collect);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        CustomUserDetails userDetails = (CustomUserDetails) obj;
        return Objects.equals(id, userDetails.getId());
    }
}
