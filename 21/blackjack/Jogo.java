public class Jogo {
    private Jogador jogador;
    private Jogador dealer;
    private Baralho baralho;

    // Construtor que aceita o nome do jogador
    public Jogo(String nomeJogador) {
        this.jogador = new Jogador(nomeJogador);
        this.dealer = new Jogador("Dealer");
        this.jogador.setDealer(this.dealer); // Define o dealer para o jogador
        this.dealer.setDealer(this.jogador); // Opcional: dealer também pode conhecer o jogador
        this.baralho = new Baralho();
    }

    public void iniciar() {
        // Embaralha o baralho
        baralho.embaralhar();

        // Distribui duas cartas para o jogador e para o dealer
        jogador.adicionarCarta(baralho.pegarCarta());
        jogador.adicionarCarta(baralho.pegarCarta());
        
        dealer.adicionarCarta(baralho.pegarCarta());
        dealer.adicionarCarta(baralho.pegarCarta());
        
        System.out.println("Jogo iniciado com o jogador: " + jogador.getNome());
    }

    // Métodos que App.java espera:
    public Carta getCartaVisivelDealer() {
        // Retorna a primeira carta do dealer, por exemplo
        if (dealer.getMao().tamanho() > 0) {
            return dealer.getMao().obter(0);
        }
        return null; // Ou lançar uma exceção se a mão estiver vazia
    }

    public int getValorMaoJogador() {
        return jogador.calcularValorMao();
    }

    public void jogadorPedeCarta() {
        Carta novaCarta = baralho.pegarCarta();
        if (novaCarta != null) {
            jogador.adicionarCarta(novaCarta);
        } else {
            System.out.println("Baralho vazio!");
        }
    }

    public void turnoDoDealerAutomatico() {
        // Lógica simples para o dealer: pedir carta até 17 ou mais
        System.out.println("Dealer revela sua segunda carta: " + dealer.getMao().obter(1).getNome());
        while (dealer.calcularValorMao() < 17) {
            System.out.println("Dealer pede carta.");
            Carta novaCarta = baralho.pegarCarta();
            if (novaCarta != null) {
                dealer.adicionarCarta(novaCarta);
                System.out.println("Dealer recebeu: " + novaCarta.getNome());
            } else {
                System.out.println("Baralho vazio!");
                break;
            }
        }
        System.out.println("Dealer para com total: " + dealer.calcularValorMao());
    }

    public int getValorMaoDealer() {
        return dealer.calcularValorMao();
    }

    public String determinarVencedor() {
        int valorJogador = jogador.calcularValorMao();
        int valorDealer = dealer.calcularValorMao();

        if (valorJogador > 21) {
            return "Jogador estourou! Dealer vence.";
        }
        if (valorDealer > 21) {
            return "Dealer estourou! Jogador vence.";
        }
        if (valorJogador == valorDealer) {
            return "Empate!";
        }
        if (valorJogador > valorDealer) {
            return "Jogador vence!";
        } else {
            return "Dealer vence!";
        }
    }

    public MinhaList<Carta> getCartasJogador() {
        return jogador.getMao();
    }

    public MinhaList<Carta> getCartasDealer() {
        return dealer.getMao();
    }
}