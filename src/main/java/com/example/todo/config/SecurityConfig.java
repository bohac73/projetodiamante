package com.example.todo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.example.todo.repository.UserRepository;
import com.example.todo.entity.User;
import org.springframework.security.oauth2.client.userinfo.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.core.user.*;
import java.util.*;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(o -> o
                        .userInfoEndpoint(u -> u.userService(this.oauth2UserService()))
                        .defaultSuccessUrl("/", true)
                );
        return http.build();
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return (userRequest) -> {
            OAuth2User oauth2User = delegate.loadUser(userRequest);

            String login = oauth2User.getAttribute("login");

            // Recupera e "fixa" o email de forma final
            String rawEmail = oauth2User.getAttribute("email");
            String resolvedEmail = rawEmail;

            if (resolvedEmail == null) {
                Object emails = oauth2User.getAttributes().get("emails");
                if (emails instanceof List<?> list && !list.isEmpty()) {
                    resolvedEmail = (String) ((Map<?, ?>) list.get(0)).get("email");
                }
                if (resolvedEmail == null) {
                    resolvedEmail = login + "@users.noreply.github.com";
                }
            }

            final String email = resolvedEmail; // agora é final

            userRepository.findByEmail(email).orElseGet(() -> {
                User u = new User();
                u.setEmail(email);
                u.setName(login);
                return userRepository.save(u);
            });

            return oauth2User;
        };
    }
}
