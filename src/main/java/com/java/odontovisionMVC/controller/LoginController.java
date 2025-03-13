package com.java.odontovisionMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam String senha, Model model) {
        if ("admin123".equals(usuario) && "admin123".equals(senha)) {
            return "redirect:/painel"; // Redireciona para o painel do administrador
        } else {
            model.addAttribute("erro", "Usuário ou senha incorretos!");
            return "login_admin"; // Retorna à página de login com mensagem de erro
        }
    }
}
