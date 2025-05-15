public interface CartaList extends MinhaList<Carta> {
    void adicionarCarta(Carta carta);
    int calcularValor();
    MinhaList<Carta> getCartas();
}