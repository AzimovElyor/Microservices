package com.example.authservice.config;

import com.example.authservice.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final String[] WHILE_LIST={
            "/api/v1/auth/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      return   http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                   authRequest->
                           authRequest.requestMatchers(WHILE_LIST).permitAll()
                                   .anyRequest().authenticated()
                )
              .addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class)
              .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .build();

    }

}
