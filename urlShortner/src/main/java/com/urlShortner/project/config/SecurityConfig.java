package com.urlShortner.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/**").authenticated() // secure APIs
                        .requestMatchers("/index.html", "/", "/style.css", "/script.js").authenticated() // UI
                        .requestMatchers("/{code:[a-zA-Z0-9]{6,10}}").permitAll() // short URLs public
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/index.html", true)
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
