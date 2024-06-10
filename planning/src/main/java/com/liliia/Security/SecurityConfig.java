package com.liliia.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/tasks").authenticated()
                                //.requestMatchers("/tasks/user").authenticated()
                                .requestMatchers("/tasks/**").authenticated()
                                .requestMatchers("/", "/login", "/register").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .failureHandler(new CustomAuthenticationFailureHandler())
                                .defaultSuccessUrl("/tasks", true)
                                .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();

        /*return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll()
                        .requestMatchers("tasks/**").authenticated())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

