import java.util.Iterator;

public class MinhaArrayList<T> implements MinhaList<T> {
    private static final int CAPACIDADE_INICIAL = 10;
    private Object[] elementos;
    private int tamanho;
    
    public MinhaArrayList() {
        this.elementos = new Object[CAPACIDADE_INICIAL];
        this.tamanho = 0;
    }
    
    @Override
    public void adicionar(T elemento) {
        garantirCapacidade();
        elementos[tamanho++] = elemento;
    }
    
    @Override
    public void adicionar(int indice, T elemento) {
        verificarIndice(indice);
        garantirCapacidade();
        
        // Mover elementos para abrir espaço
        for (int i = tamanho; i > indice; i--) {
            elementos[i] = elementos[i - 1];
        }
        
        elementos[indice] = elemento;
        tamanho++;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T obter(int indice) {
        verificarIndice(indice);
        return (T) elementos[indice];
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T remover(int indice) {
        verificarIndice(indice);
        T elementoRemovido = (T) elementos[indice];
        
        // Mover elementos para fechar o espaço
        for (int i = indice; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        
        elementos[tamanho - 1] = null; // Ajuda o garbage collector
        tamanho--;
        return elementoRemovido;
    }
    
    @Override
    public boolean removerElemento(T elemento) {
        for (int i = 0; i < tamanho; i++) {
            if (elemento.equals(elementos[i])) {
                remover(i);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int tamanho() {
        return tamanho;
    }
    
    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }
    
    @Override
    public void limpar() {
        // Limpar referências para ajudar o garbage collector
        for (int i = 0; i < tamanho; i++) {
            elementos[i] = null;
        }
        tamanho = 0;
    }
    
    @Override
    public boolean contem(T elemento) {
        return indiceDe(elemento) >= 0;
    }
    
    @Override
    public int indiceDe(T elemento) {
        for (int i = 0; i < tamanho; i++) {
            if (elemento == null ? elementos[i] == null : elemento.equals(elementos[i])) {
                return i;
            }
        }
        return -1;
    }
    
    private void garantirCapacidade() {
        if (tamanho == elementos.length) {
            int novaCapacidade = elementos.length * 2;
            Object[] novosElementos = new Object[novaCapacidade];
            
            // Copiar elementos para o novo array
            for (int i = 0; i < elementos.length; i++) {
                novosElementos[i] = elementos[i];
            }
            
            elementos = novosElementos;
        }
    }
    
    private void verificarIndice(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice: " + indice + ", Tamanho: " + tamanho);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            
            @Override
            public boolean hasNext() {
                return currentIndex < tamanho();
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return obter(currentIndex++);
            }
        };
    }
}