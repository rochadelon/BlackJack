import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Bem-vindo ao jogo de Blackjack! ===");
        
        // Obter nome do jogador
        System.out.print("Insira seu nome: ");
        System.out.flush(); // Force output to display
        String nome = scanner.nextLine();
        
        boolean continuarJogando = true;
        
        while (continuarJogando) {
            // Inicializa uma nova rodada
            Jogo jogo = new Jogo(nome);
            
            // Inicializa o jogo (embaralha e distribui as cartas iniciais)
            jogo.iniciar();
            
            // Mostra as cartas iniciais
            System.out.println("\n=== Início da Rodada ===");
            System.out.println("Suas cartas:");
            exibirCartasJogador(jogo);
            System.out.println("Carta visível do Dealer: " + jogo.getCartaVisivelDealer());
            
            // Turno do jogador
            boolean jogadorParou = false;
            while (!jogadorEstourou(jogo) && !jogadorParou) {
                System.out.println("\nSeu total: " + jogo.getValorMaoJogador());
                System.out.println("Opções: (1) Pedir carta | (2) Parar");
                System.out.print("Escolha: ");
                System.out.flush(); // Force output to display
                
                // Use nextLine() + parse em vez de nextInt()
                String escolhaStr = scanner.nextLine();
                int escolha;
                try {
                    escolha = Integer.parseInt(escolhaStr);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, digite 1 ou 2.");
                    continue;
                }
                
                if (escolha == 1) {
                    jogo.jogadorPedeCarta();
                    System.out.println("\nVocê recebeu uma nova carta.");
                    exibirCartasJogador(jogo);
                } else if (escolha == 2) {
                    jogadorParou = true;
                } else {
                    System.out.println("Opção inválida. Por favor, digite 1 ou 2.");
                }
            }
            
            // Se o jogador estourou, encerra a rodada
            if (jogadorEstourou(jogo)) {
                System.out.println("\nVocê estourou! Total: " + jogo.getValorMaoJogador());
                System.out.println("Dealer vence!");
            } else {
                // Turno do dealer
                System.out.println("\n=== Turno do Dealer ===");
                jogo.turnoDoDealerAutomatico();
                
                // Exibe resultados
                System.out.println("\n=== Resultado Final ===");
                System.out.println("Suas cartas:");
                exibirCartasJogador(jogo);
                System.out.println("Total: " + jogo.getValorMaoJogador());
                
                System.out.println("\nCartas do Dealer:");
                exibirCartasDealer(jogo);
                System.out.println("Total: " + jogo.getValorMaoDealer());
                
                // Determina o vencedor
                String resultado = jogo.determinarVencedor();
                System.out.println("\nResultado: " + resultado);
            }
            
            // Perguntar se quer jogar novamente
            System.out.print("\nJogar novamente? (S/N): ");
            System.out.flush(); // Force output to display
            String resposta = scanner.nextLine().toUpperCase();
            continuarJogando = resposta.equals("S");
        }
        
        System.out.println("Obrigado por jogar Blackjack! Até a próxima!");
        scanner.close();
    }
    
    // Método para verificar se o jogador estourou (passou de 21)
    private static boolean jogadorEstourou(Jogo jogo) {
        return jogo.getValorMaoJogador() > 21;
    }
    
    // Método para exibir as cartas do jogador
    private static void exibirCartasJogador(Jogo jogo) {
        MinhaList<Carta> cartasJogador = jogo.getCartasJogador();
        for (int i = 0; i < cartasJogador.tamanho(); i++) {
            System.out.println("- " + cartasJogador.obter(i).getNome());
        }
    }
    
    // Método para exibir as cartas do dealer
    private static void exibirCartasDealer(Jogo jogo) {
        MinhaList<Carta> cartasDealer = jogo.getCartasDealer();
        for (int i = 0; i < cartasDealer.tamanho(); i++) {
            System.out.println("- " + cartasDealer.obter(i).getNome());
        }
    }
}