package com.java.odontovisionMVC.controller;

import com.java.odontovisionMVC.model.Usuario;
import com.java.odontovisionMVC.model.EnderecoUsuario;
import com.java.odontovisionMVC.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar usuários
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "listar_usuarios"; // Página de listagem
    }

    // Exibir formulário para adicionar um novo usuário
    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "forms_usuarios"; // Página do formulário de cadastro
    }

    // Salvar um novo usuário e redirecionar para a página de confirmação
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute Usuario usuario) {
        boolean isNovo = (usuario.getId() == null); // Se ID for nulo, é um novo usuário

        // Define uma senha temporária se não estiver presente
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            usuario.setSenha("12345"); // Senha padrão (recomendado criptografar!)
        }

        // Primeiro, salva o usuário
        Usuario usuarioSalvo = usuarioService.salvar(usuario);

        // Agora, associa o endereço ao usuário e salva novamente
        if (usuario.getEndereco() != null) {
            usuario.getEndereco().setUsuario(usuarioSalvo);
            usuarioService.salvar(usuarioSalvo); // Salva novamente com endereço vinculado
        }

        // Se for um novo cadastro, redireciona para a página de sucesso
        return isNovo ? "redirect:/usuarios/cadastrado" : "redirect:/usuarios";
    }


    // Página de usuário cadastrado com sucesso
    @GetMapping("/cadastrado")
    public String usuarioCadastrado() {
        return "usuario_cadastrado"; // Página de confirmação do cadastro
    }

    // Exibir formulário para editar um usuário existente
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id).orElse(null));
        return "editar_usuario";
    }

    // Redirecionar para a página de confirmação de exclusão
    @GetMapping("/confirmar_exclusao/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id).orElse(null));
        return "confirmar_exclusao_usuario"; // Página de confirmação de exclusão
    }

    // Excluir usuário e redirecionar para a lista de usuários
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
}
