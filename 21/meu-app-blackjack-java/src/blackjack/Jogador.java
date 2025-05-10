public class Jogador {
    private String nome;
    private Mao mao;

    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new Mao();
    }

    public String getNome() {
        return nome;
    }

    public Mao getMao() {
        return mao;
    }

    public void adicionarCarta(Carta carta) {
        mao.adicionarCarta(carta);
    }

    public int calcularValorMao() {
        return mao.calcularValor();
    }

    public boolean desejaPedirCarta() {
        // Lógica para o jogador decidir se deseja pedir outra carta
        // Isso pode ser implementado com entrada do usuário ou uma estratégia automática
        return true; // Exemplo: sempre pede carta
    }
}