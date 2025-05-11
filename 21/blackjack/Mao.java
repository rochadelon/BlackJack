public class Mao {
    private List<Carta> cartas;

    public Mao() {
        this.cartas = new ArrayList<>();
    }

    public void adicionarCarta(Carta carta) {
        cartas.add(carta);
    }

    public int calcularValor() {
        int valorTotal = 0;
        int ases = 0;

        for (Carta carta : cartas) {
            valorTotal += carta.getValor();
            if (carta.getValor() == 11) {
                ases++;
            }
        }

        // Ajustar o valor total se houver ases e o valor total for maior que 21
        while (valorTotal > 21 && ases > 0) {
            valorTotal -= 10; // Considera o Ás como 1 em vez de 11
            ases--;
        }

        return valorTotal;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void exibirMao() {
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }
}