import java.util.Iterator;

public class MinhaListImpl<T> implements MinhaList<T> {
    private static final int CAPACIDADE_INICIAL = 10;
    private Object[] elementos;
    private int tamanho;
    
    public MinhaListImpl() {
        this.elementos = new Object[CAPACIDADE_INICIAL];
        this.tamanho = 0;
    }

    @Override
    public void adicionar(T elemento) {
        garantirCapacidade(tamanho + 1);
        elementos[tamanho++] = elemento;
    }

    @Override
    public void adicionar(int indice, T elemento) {
        if (indice < 0 || indice > tamanho) {
            throw new IndexOutOfBoundsException("Índice: " + indice);
        }
        
        garantirCapacidade(tamanho + 1);
        
        // Desloca elementos para a direita
        System.arraycopy(elementos, indice, elementos, indice + 1, tamanho - indice);
        elementos[indice] = elemento;
        tamanho++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T obter(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice: " + indice);
        }
        return (T) elementos[indice];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remover(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice: " + indice);
        }
        
        T elementoRemovido = (T) elementos[indice];
        
        // Desloca elementos para a esquerda
        int numElementosAMover = tamanho - indice - 1;
        if (numElementosAMover > 0) {
            System.arraycopy(elementos, indice + 1, elementos, indice, numElementosAMover);
        }
        
        elementos[--tamanho] = null; // Libera referência para GC
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
        // Libera referências para GC
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
        if (elemento == null) {
            for (int i = 0; i < tamanho; i++) {
                if (elementos[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < tamanho; i++) {
                if (elemento.equals(elementos[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            
            @Override
            public boolean hasNext() {
                return currentIndex < tamanho;
            }
            
            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return (T) elementos[currentIndex++];
            }
        };
    }

    private void garantirCapacidade(int minCapacidade) {
        if (minCapacidade > elementos.length) {
            // Cresce a capacidade em 50%
            int novaCapacidade = elementos.length * 3 / 2 + 1;
            if (novaCapacidade < minCapacidade) {
                novaCapacidade = minCapacidade;
            }
            
            // Cria um novo array e copia os elementos
            Object[] novoArray = new Object[novaCapacidade];
            System.arraycopy(elementos, 0, novoArray, 0, tamanho);
            elementos = novoArray;
        }
    }
}