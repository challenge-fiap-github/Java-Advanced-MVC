# 🦷 OdontoVision - Sistema Integrado de Gamificação e Análise Preditiva

## 📌 Sobre o Projeto
A **OdontoVision** é uma solução inovadora para clientes da **Odontoprev**, combinando **gamificação, análise preditiva por IA e integração com recompensas**. O sistema incentiva a adesão dos pacientes aos cuidados odontológicos, permitindo que acumulem pontos e participem de um programa de fidelidade.

A solução envolve:
- **📱 Aplicativo móvel (React Native):** define o planejamento de consultas e checklist diário de higiene bucal.
- **💻 Plataforma Web (C#):** utilizada pelos dentistas para registrar diagnósticos e alimentar a base de dados.
- **🧠 Inteligência Artificial (Python - Random Forest):** identifica possíveis fraudes e inconsistências nos diagnósticos.
- **🎮 Sistema de Gamificação:** recompensa os pacientes com pontos, dobrando-os se mantiverem uma boa higiene bucal.
- **🔗 Integração Futura:** conexão com **Livelo** para ampliar o catálogo de recompensas.

---

## 👥 Equipe
| Matrícula  | Nome                              |
|------------|-----------------------------------|
| **553568** | Sabrina da Motta Café            |
| **552692** | Luís Henrique Oliveira Da Silva  |
| **554199** | Matheus Duarte Oliveira          |

---

## 🛠 Tecnologias Utilizadas
- **Back-end:** Java + Spring Boot (MVC, JPA, Thymeleaf)
- **Banco de Dados:** Oracle
- **Front-end Web:** HTML + CSS + Thymeleaf
- **Mobile App:** React Native
- **Análise de Dados:** Python (Random Forest)
- **Plataforma Web para Dentistas:** C#

---

## 🗂 Arquitetura do Sistema
### 🔹 Diagrama UML
*(Adicione aqui uma imagem do diagrama UML do banco de dados.)*

### 🔹 Fluxo do Sistema
1. **Cadastro de Pacientes e Dentistas**
    - O paciente se cadastra pelo app e agenda a primeira consulta.
    - O dentista se cadastra via plataforma web e registra consultas.

2. **Acompanhamento e Recompensas**
    - O paciente segue um **checklist diário de higiene**.
    - O dentista avalia a higiene do paciente e dobra os pontos se necessário.

3. **Detecção de Fraudes com IA**
    - Após cada consulta, a IA **analisa padrões suspeitos** e gera alertas para a Odontoprev.

4. **Troca de Pontos e Recompensas**
    - No futuro, os pontos poderão ser **convertidos em benefícios na Livelo**.

---

## 📜 Instalação e Configuração
### ✅ Pré-requisitos
- JDK 17+
- Maven 3.8+
- Banco de Dados Oracle (configurar credenciais no `application.properties`)
- Node.js (para rodar o app React Native)

### 🔧 Passos para rodar o projeto
#### 1️⃣ Clonar o repositório
```sh
git clone https://github.com/SEU-USUARIO/odontovision.git
cd odontovision