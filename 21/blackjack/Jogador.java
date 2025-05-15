public class Jogador {
    private String nome;
    private CartaList mao;
    private Jogador dealer;
    
    // Construtor: Inicializa o jogador com um nome e cria uma mão vazia
    // O dealer é o adversário do jogador
    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new CartaListImpl(); // Implement this class extending MinhaListImpl<Carta>
        // Don't create a new Jogador here to avoid infinite recursion
    }
    
    // Se houver sobrecarga de construtor, use this() corretamente:
    public Jogador() {
        this("Jogador");
        // The dealer will be set separately
    }

    public String getNome() {
        return nome;
    }

    public CartaList getMao() {
        return mao;
    }
    
    public void setDealer(Jogador dealer) {
        this.dealer = dealer;
    }
    
    public Jogador getDealer() {
        return dealer;
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

    public String mostrarMao() {
        StringBuilder resultado = new StringBuilder();
        MinhaList<Carta> cartas = getMao().getCartas();
        
        for (int i = 0; i < cartas.tamanho(); i++) {
            resultado.append(cartas.obter(i).getNome());
            if (i < cartas.tamanho() - 1) {
                resultado.append(", ");
            }
        }
        
        return resultado.toString();
    }
}