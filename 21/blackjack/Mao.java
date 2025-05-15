public class Mao {
    private MinhaList<Carta> cartas;

    public Mao() {
        this.cartas = new MinhaArrayList<>();
    }

    public void adicionarCarta(Carta carta) {
        cartas.adicionar(carta);
    }

    public int calcularValor() {
        int valor = 0;
        int ases = 0;
        
        for (int i = 0; i < cartas.tamanho(); i++) {
            Carta carta = cartas.obter(i);
            valor += carta.getValor();
            if (carta.getNome().startsWith("Ás")) {
                ases++;
            }
        }
        
        // Ajustar valor dos ases se necessário
        while (valor > 21 && ases > 0) {
            valor -= 10; // Reduz o valor do Ás de 11 para 1
            ases--;
        }
        
        return valor;
    }

    public boolean temBlackjack() {
        if (cartas.tamanho() != 2) return false;
        
        int valor = 0;
        for (int i = 0; i < cartas.tamanho(); i++) {
            valor += cartas.obter(i).getValor();
        }
        
        return valor == 21;
    }

    public MinhaList<Carta> getCartas() {
        return cartas;
    }

    public void exibirMao() {
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }
}