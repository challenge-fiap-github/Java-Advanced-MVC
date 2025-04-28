package com.java.odontovisionMVC.controller;

import com.java.odontovisionMVC.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Recebe no body um JSON { "message": "texto da pergunta" },
     * chama o ChatService e devolve { "answer": "texto da resposta" }.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> payload) {
        String pergunta = payload.get("message");
        String resposta = chatService.perguntar(pergunta);

        Map<String, String> respostaMap = new HashMap<>();
        respostaMap.put("answer", resposta);

        return ResponseEntity.ok(respostaMap);
    }
}
