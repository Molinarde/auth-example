package com.example.simpleprojectungram.config;

import com.example.simpleprojectungram.service.MongoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MongoUserDetailsService userDetailsService;

    public SecurityConfig(MongoUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/gallery/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/profile/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //In-Memory
/*    @Bean
    public UserDetailsService users(){
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$oBr2HgpL6kOBC1KA.f.49Ozs2HAnjbi90OUmcKb/ECGjgR26DucrW")
                .roles("USER")
                .build();
          UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$oBr2HgpL6kOBC1KA.f.49Ozs2HAnjbi90OUmcKb/ECGjgR26DucrW")
                .roles("ADMIN", "USER")
                .build();

          return new InMemoryUserDetailsManager(user, admin);
    }*/

}
