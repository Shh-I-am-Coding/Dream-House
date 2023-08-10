package com.ssafy.happy.common.config;

import com.ssafy.happy.common.filter.JwtAuthenticationFilter;
import com.ssafy.happy.common.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(
                http.getSharedObject(AuthenticationConfiguration.class));
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
//                .antMatchers("/users/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider));
        return http.build();
    }
}
