public class Jogo {
    private Baralho baralho;
    private Jogador jogador;
    private Jogador dealer;

    public Jogo() {
        this.baralho = new Baralho();
        this.jogador = new Jogador("Jogador");
        this.dealer = new Jogador("Dealer");
    }

    // Construtor com nome do jogador
    public Jogo(String nomeJogador) {
        this.baralho = new Baralho();
        this.jogador = new Jogador(nomeJogador);
        this.dealer = new Jogador("Dealer");
    }

    public void iniciar() {
        baralho.criarBaralho();
        baralho.embaralhar(); // Embaralha o baralho antes de distribuir
        distribuirCartas();
        // Resto da lógica do jogo
    }

    private void distribuirCartas() {
        for (int i = 0; i < 2; i++) {
            jogador.adicionarCarta(baralho.removerPrimeira());
            dealer.adicionarCarta(baralho.removerPrimeira());
        }
    }

    public void mostrarResultados() {
        System.out.println("Mão do Jogador: " + jogador.mostrarMao());
        System.out.println("Mão do Dealer: " + dealer.mostrarMao());
    }

    // Obter o valor da mão do jogador
    public int getValorMaoJogador() {
        return jogador.calcularValorMao();
    }

    // Obter o valor da mão do dealer
    public int getValorMaoDealer() {
        return dealer.calcularValorMao();
    }

    // Obter a primeira carta visível do dealer
    public String getCartaVisivelDealer() {
        return dealer.getMao().getCartas().obter(0).getNome();
    }

    // Jogador pede mais uma carta
    public void jogadorPedeCarta() {
        jogador.adicionarCarta(baralho.removerPrimeira());
    }

    // Executa o turno do dealer automaticamente
    public void turnoDoDealerAutomatico() {
        // Dealer pega cartas até ter pelo menos 17
        while (dealer.calcularValorMao() < 17) {
            dealer.adicionarCarta(baralho.removerPrimeira());
            System.out.println("Dealer pegou: " + dealer.getMao().getCartas().obter(dealer.getMao().getCartas().tamanho()-1).getNome());
        }
    }

    // Obter lista de cartas do jogador
    public MinhaList<Carta> getCartasJogador() {
        return jogador.getMao().getCartas();
    }

    // Obter lista de cartas do dealer
    public MinhaList<Carta> getCartasDealer() {
        return dealer.getMao().getCartas();
    }

    // Determinar o vencedor
    public String determinarVencedor() {
        int valorJogador = jogador.calcularValorMao();
        int valorDealer = dealer.calcularValorMao();

        if (valorJogador > 21) {
            return "Você estourou! Dealer vence.";
        } else if (valorDealer > 21) {
            return "Dealer estourou! Você vence!";
        } else if (valorJogador > valorDealer) {
            return "Você vence com " + valorJogador + " pontos!";
        } else if (valorDealer > valorJogador) {
            return "Dealer vence com " + valorDealer + " pontos.";
        } else {
            return "Empate! Ambos têm " + valorJogador + " pontos.";
        }
    }
}