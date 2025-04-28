package com.java.odontovisionMVC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1) Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2) Usuário em memória (apenas para testes)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var admin = User.builder()
                .username("admin")
                .password("$2a$12$jf7otcC9kvkOrrCGmUFkvebdyjmgvcNPVElwJshtIsdWZkC6X2ZVa")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    // 3) Provedor DAO para autenticação form-based
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    // 4) AuthenticationManager (caso precise injetar manualmente)
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    // 5) Regras de segurança, formLogin, oauth2Login, logout e CSRF
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authProvider
    ) throws Exception {
        http
                // registra o provedor de autenticação form-based
                .authenticationProvider(authProvider)

                //  ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
                // CSRF: ignora apenas o endpoint /chat (AJAX)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/chat")
                )
                //  ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––

                // configuração de quais URLs são permitidas sem login
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/css/**", "/js/**", "/images/**",
                                "/login", "/error", "/oauth2/**"
                        ).permitAll()

                        // permite acesso autenticado ao /chat
                        .requestMatchers("/chat").authenticated()

                        // todas as outras URLs também exigem autenticação
                        .anyRequest().authenticated()
                )

                // configuração do login tradicional
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/painel", true)
                        .failureUrl("/login?erro")
                )

                // configuração do login via OAuth2 (GitHub, Google, etc)
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .defaultSuccessUrl("/painel", true)
                )

                // configuração do logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
        ;

        return http.build();
    }
}
