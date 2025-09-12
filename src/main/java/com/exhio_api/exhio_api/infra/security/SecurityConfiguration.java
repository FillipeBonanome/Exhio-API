package com.exhio_api.exhio_api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.authorization.AuthorizationManagers.anyOf;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()).
                sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests((auth) ->
                        auth.
                                requestMatchers(HttpMethod.POST, "/login").permitAll().
                                requestMatchers(HttpMethod.POST, "/sign-up").permitAll().

                                requestMatchers(HttpMethod.POST, "/users/**").access(anyOf(hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.PUT, "/users/**").access(anyOf(hasRole("SUPER_ADMIN"))).

                                requestMatchers(HttpMethod.POST, "/dungeons/**").access(anyOf(hasAuthority("CREATE_DUNGEON"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.PUT, "/dungeons/**").access(anyOf(hasAuthority("UPDATE_DUNGEON"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.DELETE, "/dungeons/**").access(anyOf(hasAuthority("DELETE_DUNGEON"), hasRole("SUPER_ADMIN"))).

                                requestMatchers(HttpMethod.POST, "/monsters/**").access(anyOf(hasAuthority("CREATE_MONSTER"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.PUT, "/monsters/**").access(anyOf(hasAuthority("UPDATE_MONSTER"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.DELETE, "/monsters/**").access(anyOf(hasAuthority("DELETE_MONSTER"), hasRole("SUPER_ADMIN"))).

                                requestMatchers(HttpMethod.POST, "/hunts/**").access(anyOf(hasAuthority("CREATE_HUNT"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.PUT, "/hunts/**").access(anyOf(hasAuthority("UPDATE_HUNT"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.DELETE, "/hunts/**").access(anyOf(hasAuthority("DELETE_HUNT"), hasRole("SUPER_ADMIN"))).

                                requestMatchers(HttpMethod.POST, "/spells/**").access(anyOf(hasAuthority("CREATE_SPELL"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.PUT, "/spells/**").access(anyOf(hasAuthority("UPDATE_SPELL"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.DELETE, "/spells/**").access(anyOf(hasAuthority("DELETE_SPELL"), hasRole("SUPER_ADMIN"))).

                                requestMatchers(HttpMethod.POST, "/quests/**").access(anyOf(hasAuthority("CREATE_QUEST"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.PUT, "/quests/**").access(anyOf(hasAuthority("UPDATE_QUEST"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.DELETE, "/quests/**").access(anyOf(hasAuthority("DELETE_QUEST"), hasRole("SUPER_ADMIN"))).

                                requestMatchers(HttpMethod.POST, "/vocations/**").access(anyOf(hasAuthority("CREATE_VOCATION"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.PUT, "/vocations/**").access(anyOf(hasAuthority("UPDATE_VOCATION"), hasRole("SUPER_ADMIN"))).
                                requestMatchers(HttpMethod.DELETE, "/vocations/**").access(anyOf(hasAuthority("DELETE_VOCATION"), hasRole("SUPER_ADMIN"))).

                                requestMatchers(HttpMethod.GET).permitAll().
                                anyRequest().authenticated()
                        ).
                addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).
                build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
