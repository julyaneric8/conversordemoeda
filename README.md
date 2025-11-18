# Conversor de Moedas em Java

Um projeto desenvolvido em Java que realiza conversões entre diferentes moedas utilizando dados reais fornecidos pela API **ExchangeRate API v6**.
O programa funciona pelo terminal e oferece um menu interativo para o usuário escolher a conversão desejada.
A lógica do sistema utiliza os pilares de Programação Orientada a Objetos (POO) e bibliotecas como **HttpClient** e **Gson**.

---

## Funcionalidades

* Conversão entre múltiplas moedas:

  * USD ↔ BRL
  * EUR ↔ BRL
  * GBP ↔ BRL
* Consulta em tempo real utilizando a API de taxas de câmbio.
* Menu simples no terminal.
* Conversões realizadas com base em um objeto de resposta JSON da API.
* Implementação estruturada com POO:

  * Abstração
  * Encapsulamento
  * Polimorfismo
  * Herança (onde aplicável)

---

## Tecnologias Utilizadas

* **Java**
* **Gson** (para leitura de JSON)
* **Java HttpClient** (para consumo da API)
* **ExchangeRate API v6**
  Endpoint utilizado:

  ```
  https://v6.exchangerate-api.com/v6/SUA_API_KEY/latest/USD
  ```

---

## Estrutura do Projeto

```
src/
└── main/
    └── java/
        └── br/com/seunome/conversormoedas/
            ├── Main.java
            ├── api/
            │   ├── ApiService.java
            │   └── CurrencyRateResponse.java
            ├── model/
            │   └── CurrencyConverter.java
            └── view/
                └── Menu.java
```

---

## Como Executar o Projeto

1. Clone o repositório:

   ```
   git clone https://github.com/seuusuario/conversor-moedas-java.git
   ```

2. Abra o projeto em sua IDE (IntelliJ, Eclipse ou VS Code).

3. Adicione a biblioteca **Gson**:

   * Baixe o JAR
     ou
   * Use Maven/Gradle (caso tenha configurado)

4. Insira sua **API KEY** da ExchangeRate no arquivo `ApiService.java`.

5. Rode o arquivo `Main.java`.

---

## Exemplo de Uso

Ao iniciar o programa, o menu aparecerá:

```
=== Conversor de Moedas ===

1 - USD → BRL
2 - BRL → USD
3 - EUR → BRL
4 - BRL → EUR
5 - GBP → BRL
6 - BRL → GBP
0 - Sair
```

---

## Conceitos de POO Aplicados

* **Abstração**
  Separação entre classes de API, modelo, conversão e interface de usuário.

* **Encapsulamento**
  Cada classe é responsável apenas pela sua própria função.

* **Polimorfismo**
  Menu aciona diferentes caminhos de conversão e mantém o mesmo fluxo de execução.

* **Responsabilidade Única (SRP)**
  Classes divididas em camadas:

  * API
  * Lógica
  * Interface
