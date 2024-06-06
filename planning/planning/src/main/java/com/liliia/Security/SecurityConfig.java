package com.liliia.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/tasks").hasRole("ADMIN")
                        .requestMatchers("/tasks/user").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/tasks/**").authenticated()
                        .requestMatchers("/login", "/register").permitAll()
                        .anyRequest().authenticated()
                 )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/tasks", true)
                .permitAll()
            )
            .logout(logout -> logout.permitAll())
                .csrf(csrfCustomizer -> csrfCustomizer.disable());
        return http.build();
    }


}
