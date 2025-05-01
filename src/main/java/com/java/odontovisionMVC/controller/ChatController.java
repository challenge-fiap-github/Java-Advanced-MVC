package com.java.odontovisionMVC.controller;

import com.java.odontovisionMVC.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*") // Libera para chamadas externas (frontend, etc.)
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Endpoint principal para o Chat-Ops.
     * Exemplo de chamada:
     * POST /chat
     * Body: { "message": "Quantos pacientes temos?" }
     *
     * @param payload JSON contendo {"message": "..."}
     * @return JSON com {"answer": "..."}
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> payload) {
        String pergunta = payload.get("message");
        if (pergunta == null || pergunta.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Mensagem não pode estar vazia."));
        }

        String resposta = chatService.perguntar(pergunta);
        return ResponseEntity.ok(Map.of("answer", resposta));
    }
}
