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

    @GetMapping
    public String listarDentistas(Model model) {
        model.addAttribute("dentistas", dentistaService.listarTodos());
        return "dentista/lista";
    }

    @GetMapping("/novo")
    public String novoDentista(Model model) {
        model.addAttribute("dentista", new Dentista());
        return "dentista/formulario";
    }

    @PostMapping("/salvar")
    public String salvarDentista(@ModelAttribute Dentista dentista) {
        dentistaService.salvar(dentista);
        return "redirect:/dentistas";
    }

    @GetMapping("/editar/{id}")
    public String editarDentista(@PathVariable Long id, Model model) {
        model.addAttribute("dentista", dentistaService.buscarPorId(id).orElse(null));
        return "dentista/formulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluirDentista(@PathVariable Long id) {
        dentistaService.excluir(id);
        return "redirect:/dentistas";
    }
}
