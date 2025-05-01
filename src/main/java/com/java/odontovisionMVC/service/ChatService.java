package com.java.odontovisionMVC.service;

/**
 * API para o Chat-Ops interno que responde a perguntas do admin
 * com estatísticas do sistema e permite atualizar o contexto de uso.
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

    /**
     * Atualiza o contexto do assistente para influenciar as respostas.
     * Ideal para customizações temporárias ou dinâmicas no comportamento.
     *
     * @param contexto nova instrução do sistema
     */
    void atualizarContexto(String contexto);
}
