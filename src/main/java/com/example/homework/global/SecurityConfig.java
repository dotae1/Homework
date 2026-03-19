package com.example.homework.global;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

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
                .logout((logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"성공\": \"성공적으로 로그아웃 되었습니다.\"}");
                        })));
        http
                .cors((auth) -> auth.disable());
        http
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(("/contents/**")).hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/auth/login", "/auth/join").permitAll()
                        .requestMatchers("/auth/logout", "/auth/change").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/h2/**").permitAll()
                        .anyRequest().authenticated()

                )
                .headers(((auth) -> auth
                        .frameOptions(frame -> frame.sameOrigin())))
                .exceptionHandling( ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"에러\": \"Unauthorized\"}");
                        }));
        http.sessionManagement((session) -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false));
        http.sessionManagement((auth) -> auth
                .sessionFixation().changeSessionId());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
