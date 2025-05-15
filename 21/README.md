# meu-app-blackjack-java

## Descrição do Projeto

Este projeto é um jogo de Blackjack implementado em Java. O objetivo do jogo é que os jogadores tentem obter uma mão com um valor total mais próximo de 21 do que a mão do dealer, sem ultrapassar esse valor.

## Estrutura do Projeto

O projeto possui a seguinte estrutura de diretórios:
**Projeto Blackjack**

└─── **Estrutura de Diretórios**
    ├─── **src/blackjack** (Código Fonte Principal)
    │   ├─── App.java
    │   ├─── Baralho.java
    │   ├─── Carta.java
    │   ├─── CartaList.java (Interface)
    │   ├─── CartaListImpl.java
    │   ├─── Jogador.java
    │   ├─── Jogo.java
    │   ├─── Mao.java (Nota: No código mais recente, a lógica de 'Mao' parece estar integrada em 'Jogador' através de 'CartaList')
    │   ├─── MinhaArrayList.java (Implementação de Lista Customizada)
    │   ├─── MinhaList.java (Interface de Lista Customizada)
    │   └─── MinhaListImpl.java (Implementação de Lista Customizada)
    ├─── **lib** (Bibliotecas Externas - se houver)
    ├─── **.vscode** (Configurações do VS Code)
    │   └─── settings.json
    └─── **.idea** (Configurações do IntelliJ IDEA - se houver)
        └─── (arquivos de configuração como  misc.xml, modules.xml, artifacts, etc.)

└─── **Classes e Funcionalidades**

    ├─── **App.java** (Ponto de Entrada e Interface com Usuário)
    │   └─── **Funcionalidades:**
    │       ├─── `main()`: Método principal, inicia o jogo.
    │       ├─── Gerencia o loop principal do jogo (jogar novamente).
    │       ├─── Obtém entrada do jogador (nome, pedir carta/parar).
    │       ├─── Exibe informações do jogo (cartas, totais, resultados).
    │       ├─── Interage com a classe `Jogo` para conduzir as rodadas.
    │       └─── Métodos auxiliares: `jogadorEstourou()`, `exibirCartasJogador()`, `exibirCartasDealer()`.

    ├─── **Jogo.java** (Controlador Principal da Lógica do Jogo)
    │   ├─── **Atributos:**
    │   │   ├─── `jogador`: Objeto `Jogador` (representa o usuário).
    │   │   ├─── `dealer`: Objeto `Jogador` (representa a "casa").
    │   │   └─── `baralho`: Objeto `Baralho`.
    │   └─── **Funcionalidades:**
    │       ├─── Construtor `Jogo(String nomeJogador)`: Inicializa jogadores e baralho.
    │       ├─── `iniciar()`: Embaralha o baralho e distribui as cartas iniciais.
    │       ├─── `getCartaVisivelDealer()`: Retorna a carta visível do dealer.
    │       ├─── `getValorMaoJogador()`: Retorna o valor da mão do jogador.
    │       ├─── `jogadorPedeCarta()`: Adiciona uma carta à mão do jogador.
    │       ├─── `turnoDoDealerAutomatico()`: Lógica para as jogadas do dealer.
    │       ├─── `getValorMaoDealer()`: Retorna o valor da mão do dealer.
    │       ├─── `determinarVencedor()`: Compara as mãos e define o vencedor.
    │       ├─── `getCartasJogador()`: Retorna a lista de cartas do jogador.
    │       └─── `getCartasDealer()`: Retorna a lista de cartas do dealer.

    ├─── **Jogador.java** (Representa um Participante do Jogo)
    │   ├─── **Atributos:**
    │   │   ├─── `nome`: String.
    │   │   ├─── `mao`: Objeto `CartaList` (para armazenar as cartas do jogador).
    │   │   └─── `dealer`: Referência ao `Jogador` que é o dealer (para interação).
    │   └─── **Funcionalidades:**
    │       ├─── Construtor `Jogador(String nome)`: Inicializa o nome e a mão.
    │       ├─── `adicionarCarta(Carta carta)`: Adiciona uma carta à mão.
    │       ├─── `calcularValorMao()`: Calcula o valor total das cartas na mão (usando `CartaList.calcularValor()`).
    │       ├─── `mostrarMao()`: Retorna uma representação em string das cartas na mão.
    │       └─── Getters e Setters para os atributos.

    ├─── **Baralho.java** (Representa o Baralho de Cartas)
    │   ├─── **Estrutura Interna:**
    │   │   └─── Lista Duplamente Encadeada (usando classe interna `Node`).
    │   ├─── **Atributos:**
    │   │   ├─── `cabeca`: Nó inicial da lista.
    │   │   ├─── `cauda`: Nó final da lista.
    │   │   └─── `tamanho`: Número de cartas no baralho.
    │   └─── **Funcionalidades:**
    │       ├─── Construtor `Baralho()`: Inicializa e cria o baralho padrão.
    │       ├─── `criarBaralho()`: Popula o baralho com 52 cartas (naipes e valores).
    │       ├─── `adicionar(Carta carta)`: (Privado) Adiciona uma carta à estrutura interna.
    │       ├─── `embaralhar()`: Reorganiza as cartas aleatoriamente.
    │       ├─── `pegarCarta()`: Remove e retorna a carta do topo do baralho.
    │       ├─── `removerPrimeira()` / `removerUltima()`: Manipulação da lista.
    │       ├─── `tamanho()`: Retorna o número de cartas.
    │       └─── `estaVazio()`: Verifica se o baralho está vazio.

    ├─── **Carta.java** (Representa uma Carta Individual)
    │   ├─── **Atributos:**
    │   │   ├─── `nome`: String (e.g., "Ás de Copas", "7 de Espadas").
    │   │   └─── `valor`: int (valor da carta no Blackjack).
    │   └─── **Funcionalidades:**
    │       ├─── Construtor `Carta(String nome, int valor)`.
    │       ├─── `getNome()`: Retorna o nome da carta.
    │       ├─── `getValor()`: Retorna o valor da carta.
    │       ├─── `toString()`: Representação em String da carta.
    │       └─── `equals()` e `hashCode()`: Para comparações.

    ├─── **Mao.java** (Representa a Mão de Cartas de um Jogador - *Nota: No código mais recente, esta funcionalidade parece integrada em Jogador usando CartaList*)
    │   ├─── **Atributos:**
    │   │   └─── `cartas`: `MinhaList<Carta>`.
    │   └─── **Funcionalidades:**
    │       ├─── `adicionarCarta(Carta carta)`.
    │       ├─── `calcularValor()`: Calcula o valor da mão, ajustando o Ás.
    │       ├─── `temBlackjack()`.
    │       └─── `getCartas()`.

    ├─── **Interfaces e Implementações de Lista Customizadas**
    │   ├─── **MinhaList<T>.java** (Interface)
    │   │   └─── **Funcionalidades (Contrato):**
    │   │       ├─── `adicionar(T elemento)`
    │   │       ├─── `adicionar(int indice, T elemento)`
    │   │       ├─── `obter(int indice)`
    │   │       ├─── `remover(int indice)`
    │   │       ├─── `removerElemento(T elemento)`
    │   │       ├─── `tamanho()`
    │   │       ├─── `estaVazia()`
    │   │       ├─── `limpar()`
    │   │       ├─── `contem(T elemento)`
    │   │       ├─── `indiceDe(T elemento)`
    │   │       ├─── `iterator()`
    │   │       └─── Métodos default para `adicionarCarta`, `calcularValor`, `getCartas` (com tratamento para `Carta`).
    │   ├─── **MinhaArrayList<T>.java** (Implementação de `MinhaList` usando Array)
    │   │   └─── **Funcionalidades:**
    │   │       ├─── Implementa todos os métodos da interface `MinhaList`.
    │   │       └─── `garantirCapacidade()`: (Privado) Para redimensionamento do array interno.
    │   ├─── **MinhaListImpl<T>.java** (Outra Implementação de `MinhaList` - similar a `MinhaArrayList`)
    │   │   └─── **Funcionalidades:**
    │   │       ├─── Implementa todos os métodos da interface `MinhaList`.
    │   │       └─── `garantirCapacidade()`: (Privado) Para redimensionamento do array interno.
    │   ├─── **CartaList.java** (Interface, estende `MinhaList<Carta>`)
    │   │   └─── **Funcionalidades (Contrato específico para lista de cartas):**
    │   │       ├─── `adicionarCarta(Carta carta)`
    │   │       ├─── `calcularValor()`
    │   │       └─── `getCartas()`
    │   └─── **CartaListImpl.java** (Implementação de `CartaList`, estende `MinhaListImpl<Carta>`)
    │       └─── **Funcionalidades:**
    │           ├─── Implementa os métodos da interface `CartaList`.
    │           └─── Reutiliza a lógica de `MinhaListImpl` para `Carta`.

└─── **Outros Arquivos**
    ├─── **README.md**: Descrição do projeto, estrutura, como executar.
    └─── **RelatorioJogo21.pdf**: Documentação detalhada do projeto, incluindo estruturas de dados, implementação e justificativas.

## Como Executar o Jogo

1. Certifique-se de ter o Java instalado em sua máquina.
2. Clone este repositório ou baixe os arquivos do projeto.
3. Navegue até o diretório do projeto.
4. Compile os arquivos Java usando o comando:
   ```
   javac src/blackjack/*.java
   ```
5. Execute o jogo com o comando:
   ```
   java src/blackjack/App
   ```

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.