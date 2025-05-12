import java.util.Iterator;

public interface MinhaList<T> extends Iterable<T> {
    void adicionar(T elemento);
    void adicionar(int indice, T elemento);
    T obter(int indice);
    T remover(int indice);
    boolean removerElemento(T elemento);
    int tamanho();
    boolean estaVazia();
    void limpar();
    boolean contem(T elemento);
    int indiceDe(T elemento);
    
    @Override
    Iterator<T> iterator();
    
    // Exemplo de uso da lista com a classe Carta (atualize para usar o novo construtor)
    public static void main(String[] args) {
        MinhaList<Carta> cartas = new MinhaListImpl<>(); // Você precisará implementar essa classe
        
        // Adicionando cartas (agora com valor)
        cartas.adicionar(new Carta("As de Espadas", 11));
        cartas.adicionar(new Carta("Rei de Copas", 10));
        cartas.adicionar(new Carta("Dama de Ouros", 10));

        // Removendo uma carta
        cartas.removerElemento(new Carta("Rei de Copas", 10));

        // Verificando se uma carta está na lista
        boolean temAsEspadas = cartas.contem(new Carta("As de Espadas", 11));

        // Obtendo o tamanho da lista
        int tamanho = cartas.tamanho();

        // Limpando a lista
        cartas.limpar();
        
        // Agora podemos usar for-each
        for (Carta carta : cartas) {
            System.out.println(carta.getNome() + " - " + carta.getValor());
        }
    }
    
    // For handling Carta-specific operations, we need an explicit cast or bound
    default void adicionarCarta(Carta carta) {
        // This method will only work correctly when T is Carta
        try {
            // Safe because the implementation is responsible for type safety
            this.adicionar((T) carta);
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException("This list doesn't support Carta elements");
        }
    }

    default int calcularValor() {
        int valor = 0;
        int ases = 0;
        
        for (int i = 0; i < tamanho(); i++) {
            if (!(obter(i) instanceof Carta)) {
                throw new UnsupportedOperationException("This method only works with Carta elements");
            }
            
            Carta carta = (Carta) obter(i);
            valor += carta.getValor();
            if (carta.getNome().startsWith("Ás")) {
                ases++;
            }
        }
        
        // Adjust ace values if needed
        while (valor > 21 && ases > 0) {
            valor -= 10;
            ases--;
        }
        
        return valor;
    }

    @SuppressWarnings("unchecked")
    default MinhaList<Carta> getCartas() {
        // This cast is only safe when T is Carta
        if (tamanho() > 0 && obter(0) instanceof Carta) {
            return (MinhaList<Carta>)this;
        }
        throw new UnsupportedOperationException("This list doesn't contain Carta elements");
    }
}

