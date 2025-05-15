public class CartaListImpl extends MinhaListImpl<Carta> implements CartaList {
    
    @Override
    public void adicionarCarta(Carta carta) {
        adicionar(carta);
    }
    
    @Override
    public int calcularValor() {
        int valor = 0;
        int ases = 0;
        
        for (int i = 0; i < tamanho(); i++) {
            Carta carta = obter(i);
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
    
    @Override
    public MinhaList<Carta> getCartas() {
        return this;
    }
}