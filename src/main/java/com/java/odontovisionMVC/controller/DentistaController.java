package com.java.odontovisionMVC.controller;

import com.java.odontovisionMVC.model.Dentista;
import com.java.odontovisionMVC.model.EnderecoClinica;
import com.java.odontovisionMVC.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    // Listar dentistas
    @GetMapping
    public String listarDentistas(Model model) {
        model.addAttribute("dentistas", dentistaService.listarTodos());
        return "listar_dentistas"; // Página de listagem
    }

    // Exibir formulário para adicionar um novo dentista
    @GetMapping("/novo")
    public String novoDentista(Model model) {
        model.addAttribute("dentista", new Dentista());
        return "dentista_form"; // Página do formulário de cadastro
    }

    @PostMapping("/salvar")
    public String salvarDentista(@ModelAttribute Dentista dentista) {
        boolean isNovo = (dentista.getId() == null); // Se ID for nulo, é um novo dentista

        // Primeiro, salva o dentista
        Dentista dentistaSalvo = dentistaService.salvar(dentista);

        // Agora, associa o endereço ao dentista e salva novamente
        if (dentista.getEndereco() != null) {
            dentista.getEndereco().setDentista(dentistaSalvo);
            dentistaService.salvar(dentistaSalvo);
        }

        // Se for um novo cadastro, redireciona para a página de sucesso
        if (isNovo) {
            return "redirect:/dentistas/cadastrado";
        } else {
            // Se for uma edição, redireciona para a lista de dentistas
            return "redirect:/dentistas";
        }
    }


    // Página de dentista cadastrado com sucesso
    @GetMapping("/cadastrado")
    public String dentistaCadastrado() {
        return "dentista_cadastrado"; // Página de confirmação do cadastro
    }

    // Exibir formulário para editar um dentista existente
    @GetMapping("/editar/{id}")
    public String editarDentista(@PathVariable Long id, Model model) {
        model.addAttribute("dentista", dentistaService.buscarPorId(id).orElse(null));
        return "editar_dentista";
    }

    // Redirecionar para a página de confirmação de exclusão
    @GetMapping("/confirmar_exclusao/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        model.addAttribute("dentista", dentistaService.buscarPorId(id).orElse(null));
        return "confirmar_exclusao_dentista"; // Página de confirmação de exclusão
    }

    // Excluir dentista e redirecionar para a lista de dentistas
    @GetMapping("/excluir/{id}")
    public String excluirDentista(@PathVariable Long id) {
        dentistaService.excluir(id);
        return "redirect:/dentistas";
    }

    // Página do painel administrativo
    @GetMapping("/painel")
    public String painelAdmin() {
        return "painel_admin";
    }
}
