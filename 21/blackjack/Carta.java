public class Carta {
    private String nome;
    private int valor;

    public Carta(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return nome + " (" + valor + ")";
    }
}