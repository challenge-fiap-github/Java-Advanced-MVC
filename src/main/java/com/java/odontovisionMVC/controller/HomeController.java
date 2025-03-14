package com.java.odontovisionMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Define a página inicial ao acessar "/"
    @GetMapping("/")
    public String home() {
        return "index"; // Redireciona para a página index.html
    }
}
