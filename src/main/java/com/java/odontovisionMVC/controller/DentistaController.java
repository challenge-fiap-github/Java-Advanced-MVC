package com.java.odontovisionMVC.controller;

import org.springframework.ui.Model;
import com.java.odontovisionMVC.model.Dentista;
import com.java.odontovisionMVC.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    // Lista todos os dentistas e exibe a página correta
    @GetMapping
    public String listarDentistas(Model model) {
        model.addAttribute("dentistas", dentistaService.listarTodos());
        return "listar_dentistas"; // Nome da página Thymeleaf correta
    }

    // Exibe o formulário para adicionar um novo dentista
    @GetMapping("/novo")
    public String novoDentista(Model model) {
        model.addAttribute("dentista", new Dentista());
        return "dentista_form"; // Página Thymeleaf para cadastro
    }

    // Salva um novo dentista ou atualiza um existente
    @PostMapping("/salvar")
    public String salvarDentista(@ModelAttribute Dentista dentista) {
        dentistaService.salvar(dentista);
        return "redirect:/dentistas";
    }

    // Exibe o formulário para editar um dentista existente
    @GetMapping("/editar/{id}")
    public String editarDentista(@PathVariable Long id, Model model) {
        model.addAttribute("dentista", dentistaService.buscarPorId(id).orElse(null));
        return "editar_dentista";
    }

    // Exclui um dentista pelo ID
    @GetMapping("/excluir/{id}")
    public String excluirDentista(@PathVariable Long id) {
        dentistaService.excluir(id);
        return "redirect:/dentistas";
    }

    // Página do painel administrativo
    @GetMapping("/painel")
    public String painelAdmin() {
        return "painel_admin"; // Redireciona para o painel Thymeleaf
    }
}