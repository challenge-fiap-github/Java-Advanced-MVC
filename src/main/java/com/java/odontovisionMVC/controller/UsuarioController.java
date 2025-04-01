package com.java.odontovisionMVC.controller;

import com.java.odontovisionMVC.dto.UsuarioDto;
import com.java.odontovisionMVC.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    // Injeção de dependência por construtor (boa prática)
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Exibe a lista de usuários
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "listar_usuarios";
    }

    // Abre o formulário de criação de novo usuário
    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", UsuarioDto.vazio()); // Utiliza DTO com valores default
        return "forms_usuarios";
    }

    // Salva ou atualiza um usuário com base na presença do ID
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute UsuarioDto usuarioDto) {
        boolean isNovo = (usuarioDto.getId() == null);
        usuarioService.salvar(usuarioDto);
        return isNovo ? "redirect:/usuarios/cadastrado" : "redirect:/usuarios";
    }

    // Página de confirmação de cadastro
    @GetMapping("/cadastrado")
    public String usuarioCadastrado() {
        return "usuario_cadastrado";
    }

    // Exibe o formulário de edição populado com os dados do usuário
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        UsuarioDto usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "editar_usuario";
    }

    // Página para confirmar exclusão
    @GetMapping("/confirmar_exclusao/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        UsuarioDto usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "confirmar_exclusao_usuario";
    }

    // Executa a exclusão do usuário
    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        usuarioService.excluir(id);
        return "redirect:/usuarios";
    }

    // Redireciona para o painel administrativo
    @GetMapping("/painel")
    public String painelAdmin() {
        return "painel_admin";
    }

    // Trata a conversão de datas de String para LocalDate (formulários HTML)
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }
}
