package com.java.odontovisionMVC.controller;

import com.java.odontovisionMVC.dto.DentistaDto;
import com.java.odontovisionMVC.service.DentistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dentistas") // Define que todas as rotas da classe começarão com /dentistas
public class DentistaController {

    private final DentistaService dentistaService;

    // Injeta a dependência via construtor (boas práticas de injeção de dependência)
    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping
    public String listarDentistas(Model model) {
        // Busca todos os dentistas e adiciona ao modelo para renderizar na view "listar_dentistas"
        model.addAttribute("dentistas", dentistaService.listarTodos());
        return "listar_dentistas";
    }

    @GetMapping("/novo")
    public String novoDentista(Model model) {
        // Cria um DentistaDto vazio (usando método de fábrica `vazio()`) e envia para o formulário
        model.addAttribute("dentista", DentistaDto.vazio());
        return "dentista_form";
    }

    @PostMapping("/salvar")
    public String salvarDentista(@ModelAttribute DentistaDto dentistaDto) {
        // Verifica se é um novo dentista baseado na ausência de ID
        boolean isNovo = (dentistaDto.getId() == null);

        // Salva o dentista e seu endereço via service
        dentistaService.salvar(dentistaDto);

        // Redireciona para uma rota diferente dependendo se é criação ou edição
        return isNovo ? "redirect:/dentistas/cadastrado" : "redirect:/dentistas";
    }

    @GetMapping("/cadastrado")
    public String dentistaCadastrado() {
        // View de confirmação após cadastro
        return "dentista_cadastrado";
    }

    @GetMapping("/editar/{id}")
    public String editarDentista(@PathVariable Long id, Model model) {
        // Busca o dentista pelo ID e envia para view de edição
        model.addAttribute("dentista", dentistaService.buscarPorId(id));
        return "editar_dentista";
    }

    @GetMapping("/confirmar_exclusao/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        // Busca dentista e renderiza tela de confirmação de exclusão
        model.addAttribute("dentista", dentistaService.buscarPorId(id));
        return "confirmar_exclusao_dentista";
    }

    @GetMapping("/excluir/{id}")
    public String excluirDentista(@PathVariable Long id) {
        // Deleta o dentista e redireciona para a listagem
        dentistaService.excluir(id);
        return "redirect:/dentistas";
    }

    @GetMapping("/painel")
    public String painelAdmin() {
        // Rota opcional para uma tela administrativa
        return "painel_admin";
    }
}
