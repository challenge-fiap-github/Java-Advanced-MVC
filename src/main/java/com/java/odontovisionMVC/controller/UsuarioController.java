package com.java.odontovisionMVC.controller;

import com.java.odontovisionMVC.model.Usuario;
import com.java.odontovisionMVC.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar usuários
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "listar_usuarios";
    }

    // Exibir formulário para adicionar um novo usuário
    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "forms_usuarios";
    }

    // Salvar ou atualizar usuário
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute Usuario usuario) {
        boolean isNovo = (usuario.getId() == null);

        if (isNovo) {
            // Se for um novo cadastro e a senha não estiver definida, atribuir uma senha padrão
            if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
                usuario.setSenha("12345"); // Senha padrão (apenas para testes)
            }
        } else {
            // Se for edição, manter a senha original
            Usuario usuarioExistente = usuarioService.buscarPorId(usuario.getId()).orElse(null);
            if (usuarioExistente != null) {
                usuario.setSenha(usuarioExistente.getSenha()); // Mantém a senha anterior
            }
        }

        // Salva o usuário e seu endereço se estiver presente
        if (usuario.getEndereco() != null) {
            usuario.getEndereco().setUsuario(usuario);
        }

        usuarioService.salvar(usuario);

        // Redireciona de acordo com a ação (novo cadastro ou edição)
        return isNovo ? "redirect:/usuarios/cadastrado" : "redirect:/usuarios";
    }


    // Página de sucesso após cadastro
    @GetMapping("/cadastrado")
    public String usuarioCadastrado() {
        return "usuario_cadastrado";
    }

    // Exibir formulário de edição
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);
        if (usuarioOpt.isPresent()) {
            model.addAttribute("usuario", usuarioOpt.get());
            return "editar_usuario";
        } else {
            return "redirect:/usuarios"; // Se não encontrar, volta para listagem
        }
    }

    // Página de confirmação de exclusão
    @GetMapping("/confirmar_exclusao/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);
        usuarioOpt.ifPresent(usuario -> model.addAttribute("usuario", usuario));
        return "confirmar_exclusao_usuario";
    }

    // Excluir usuário
    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        usuarioService.excluir(id);
        return "redirect:/usuarios";
    }

    // Página do painel administrativo
    @GetMapping("/painel")
    public String painelAdmin() {
        return "painel_admin";
    }

    // Tratamento de datas automaticamente pelo Spring
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