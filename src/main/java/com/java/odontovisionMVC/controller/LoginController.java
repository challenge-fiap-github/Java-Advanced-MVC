package com.java.odontovisionMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam String senha, Model model) {
        if ("admin123".equals(usuario) && "admin123".equals(senha)) {
            return "redirect:/painel_admin"; // Redireciona para o painel administrativo
        }
        model.addAttribute("erro", "Usuário ou senha incorretos!");
        return "login"; // Retorna para a página de login em caso de erro
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
}
