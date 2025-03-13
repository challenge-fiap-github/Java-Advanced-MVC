package com.java.odontovisionMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login_admin"; // Certifique-se de que este é o nome correto do arquivo HTML
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String usuario, @RequestParam String senha, Model model) {
        if ("admin123".equals(usuario) && "admin123".equals(senha)) {
            return "redirect:/painel"; // Redireciona para o painel admin
        } else {
            model.addAttribute("erro", "Usuário ou senha incorretos!");
            return "login_admin"; // Retorna a tela de login com erro
        }
    }

    @GetMapping("/painel")
    public String painelAdmin() {
        return "painel_admin"; // Nome correto do arquivo HTML
    }
}
