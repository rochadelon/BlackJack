public interface MinhaList<T> {
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
}