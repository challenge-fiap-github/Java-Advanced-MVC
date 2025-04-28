package com.java.odontovisionMVC.service;

/**
 * API para o Chat-Ops interno que responde a perguntas do admin
 * com estatísticas do sistema.
 */
public interface ChatService {

    /**
     * Recebe uma pergunta em linguagem natural e devolve
     * a resposta gerada pelo LLM.
     *
     * @param mensagem texto da pergunta
     * @return resposta gerada
     */
    String perguntar(String mensagem);
}
