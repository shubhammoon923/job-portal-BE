package com.jobportal.jobportal.config;

import com.jobportal.jobportal.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF for Postman/API testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Allow public endpoints
                        .anyRequest().authenticated()              // Require auth for all other endpoints
                )
                .formLogin(form -> form     // Optional: enable default login page
                        .loginPage("/login") // Customize this if you have a login page
                        .permitAll()
                )
                .httpBasic(withDefaults()) // Enable basic auth (for Postman)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // Passwords stored in plain text (for dev only!)
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // Optional: expose UserDetailsService as a bean
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}
