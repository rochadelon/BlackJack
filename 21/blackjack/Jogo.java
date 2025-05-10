public class Jogo {
    private Baralho baralho;
    private Jogador jogador;
    private Jogador dealer;

    public Jogo() {
        this.baralho = new Baralho();
        this.jogador = new Jogador("Jogador");
        this.dealer = new Jogador("Dealer");
    }

    public void iniciar() {
        baralho.criarBaralho();
        baralho.embaralhar();
        distribuirCartas();
        // Lógica do jogo vai aqui
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

    // Outros métodos para gerenciar o fluxo do jogo, ações do jogador, etc.
}