// SecurityConfig.java
package com.example.backend.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // HttpMethod import 추가
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 🔽 이 부분에 리뷰 조회 경로를 추가합니다.
                        .requestMatchers(HttpMethod.GET, "/api/hotels/*/reviews/**").permitAll() // 특정 호텔의 리뷰 조회 API는 GET 요청에 대해 허용
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api/travel-packages/**",
                                "/api/hotels/filter",
                                "/api/hotels/detail/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}