package com.java.odontovisionMVC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    // 4) Expor AuthenticationManager para uso no controller de login manual (se necessário)
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    // 5) SecurityFilterChain com regras de acesso, formLogin e oauth2Login
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authProvider
    ) throws Exception {
        http
                // registra o provedor de autenticação form-based
                .authenticationProvider(authProvider)

                // configura quem pode acessar o quê
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/css/**", "/js/**", "/images/**",
                                "/login", "/error", "/oauth2/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // configuração do login tradicional
                .formLogin(form -> form
                        .loginPage("/login")                 // nossa página MVC
                        .loginProcessingUrl("/login")        // POST /login
                        .defaultSuccessUrl("/painel", true)  // após sucesso
                        .failureUrl("/login?erro")           // em caso de falha
                )

                // configuração do login via OAuth2 (GitHub, Google, etc)
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")                 // reaproveita /login
                        .defaultSuccessUrl("/painel", true)  // após sucesso OAuth
                )

                // logout simples
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )

        ;

        return http.build();
    }
}
