package com.example.homework.global;

import com.example.homework.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberRepository memberRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((auth) -> auth.disable());
        http
                .formLogin((auth) -> auth.disable());
        http
                .httpBasic((auth) -> auth.disable());
        http
                .logout((auth) -> auth.disable());
        http
                .cors((auth) -> auth.disable());
        http
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers(("/contents/**")).hasAnyRole("USER, ADMIN")
                        .requestMatchers(("/logout")).hasAnyRole("USER, ADMIN")
                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/auth/join").permitAll()
                )
                .headers(((auth) -> auth
                        .frameOptions(frame -> frame.sameOrigin())))
                .exceptionHandling( ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"error\": \"Unauthorized\"}");
                        }));
        http.sessionManagement((session) -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true));
        http.sessionManagement((auth) -> auth
                .sessionFixation().changeSessionId());

        return http.build();
    }
}
