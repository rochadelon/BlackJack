import java.util.NoSuchElementException;
import java.util.Random;

public class Baralho {

    private Node cabeca; // Início da lista
    private Node cauda;  // Fim da lista
    private int tamanho=2; // Tamanho da lista

    // Classe Node para a lista duplamente encadeada
    private class Node {
        Carta carta;
        Node proximo;
        Node anterior;

        public Node(Carta carta) {
            this.carta = carta;
            this.proximo = null;
            this.anterior = null;
        }
    }

    // Construtor: Inicializa os pontos do 
    public Baralho(){
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
        criarBaralho(); 
    }

    // Adicionar uma carta no final da lista
    private void adicionar(Carta carta) {
        Node novoNode = new Node(carta);

        if (cabeca == null) {
            // Lista vazia
            cabeca = novoNode;
            cauda = novoNode;
        } else {
            // Adiciona no final
            cauda.proximo = novoNode;
            novoNode.anterior = cauda;
            cauda = novoNode;
        }
        tamanho++;
    }

    // Método para criar o baralho completo
    public void criarBaralho() {
        // Naipes
        String[] naipes = {"Copas", "Espadas", "Ouros", "Paus"};

        // Valores numéricos (2 a 10)
        for (String naipe : naipes) {
            for (int valor = 2; valor <= 10; valor++) {
                adicionar(new Carta(valor + " de " + naipe, valor));
            }
        }

        // Cartas com valor 10 (Valete, Dama, Rei)
        String[] figuras = {"Valete", "Dama", "Rei"};
        for (String naipe : naipes) {
            for (String figura : figuras) {
                adicionar(new Carta(figura + " de " + naipe, 10));
            }
        }

        // Ás (pode valer 1 ou 11)
        for (String naipe : naipes) {
            adicionar(new Carta("Ás de " + naipe, 11)); // Por padrão, usamos 11 como valor inicial
        }
    }

    // Método para exibir todas as cartas do baralho (do início ao fim)
    public void exibirBaralho() {
        Node atual = cabeca;
        while (atual != null) {
            System.out.println(atual.carta);
            atual = atual.proximo;
        }
    }

    // Método para exibir as cartas em ordem inversa (do fim para o início)
    public void exibirBaralhoReverso() {
        Node atual = cauda;
        while (atual != null) {
            System.out.println(atual.carta);
            atual = atual.anterior;
        }
    }

    // Retorna o número de cartas no baralho
    public int tamanho() {
        return tamanho;
    }

    // Verifica se o baralho está vazio
    public boolean estaVazio() {
        return tamanho == 0;
    }

    // Remove a primeira carta do baralho (do topo)
    public Carta removerPrimeira() {
        if (estaVazio()) {
            throw new NoSuchElementException("O baralho está vazio");
        }

        Carta cartaRemovida = cabeca.carta;

        if (cabeca == cauda) {
            // Só tem um elemento
            cabeca = null;
            cauda = null;
        } else {
            cabeca = cabeca.proximo;
            cabeca.anterior = null;
        }
        tamanho--;
        return cartaRemovida;
    }

    // Remove a última carta do baralho
    public Carta removerUltima() {
        if (estaVazio()) {
            throw new NoSuchElementException("O baralho está vazio");
        }

        Carta cartaRemovida = cauda.carta;

        if (cabeca == cauda) {
            // Só tem um elemento
            cabeca = null;
            cauda = null;
        } else {
            cauda = cauda.anterior;
            cauda.proximo = null;
        }
        tamanho--;
        return cartaRemovida;
    }

    public void embaralhar() {
        if (tamanho <= 1) return; // Não precisa embaralhar
        
        Random random = new Random();
        MinhaList<Carta> cartas = new MinhaArrayList<>();
        
        // Extrair todas as cartas para uma MinhaArrayList
        while (!estaVazio()) {
            cartas.adicionar(removerPrimeira());
        }
        
        // Reinserir em ordem aleatória
        while (cartas.tamanho() > 0) {
            int indiceAleatorio = random.nextInt(cartas.tamanho());
            adicionar(cartas.remover(indiceAleatorio));
        }
        
        System.out.println("Baralho embaralhado com sucesso!");
    }

    public Carta pegarCarta() {
        if (estaVazio()) {
            System.out.println("O baralho está vazio!");
            return null;
        }
        return removerPrimeira(); // Remove and return the top card
    }
}