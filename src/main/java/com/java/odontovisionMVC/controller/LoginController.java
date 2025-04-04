package com.java.odontovisionMVC.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável pela autenticação do administrador.
 * Utiliza autenticação manual com Spring Security, permitindo controle customizado sobre erros e fluxo de login.
 */
@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;

    /**
     * Injeta o AuthenticationManager configurado no SecurityConfig.
     */
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Exibe a página de login.
     * Se houver erro (parâmetro ?erro na URL), exibe mensagem no HTML.
     */
    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "erro", required = false) String erro, Model model) {
        if (erro != null) {
            model.addAttribute("erro", "Usuário ou senha incorretos!");
        }
        return "login_admin"; // Nome do template HTML de login
    }

    /**
     * Processa o login do administrador.
     * Faz autenticação manual via AuthenticationManager e, se válida, realiza login via request.login().
     * Caso contrário, exibe mensagem de erro personalizada.
     */
    @PostMapping("/login")
    public String processarLogin(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpServletRequest request,
                                 Model model) {
        try {
            // Autentica manualmente usando Spring Security
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Se autenticado com sucesso, registra a sessão manualmente
            request.login(username, password);

            return "redirect:/painel"; // Redireciona ao painel
        } catch (AuthenticationException | ServletException e) {
            // Em caso de falha, exibe erro de autenticação no formulário
            model.addAttribute("erro", "Usuário ou senha incorretos!");
            return "login_admin";
        }
    }

    /**
     * Página principal após login.
     */
    @GetMapping("/painel")
    public String painelAdmin() {
        return "painel_admin"; // Nome do template HTML do painel
    }
}
