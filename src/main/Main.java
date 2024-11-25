package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import types.AbstractMastermindGame;
import types.BinaryColour;
import types.BullsAndCows;
import types.Code;
import types.Colour;
import types.MultiColour;
import types.MultiColourMastermindGame;

public class Main {
	
    // Tamanho do código a ser adivinhado.
    final static int codeSize = 4;

    // Opção para o jogo Mastermind Original.
    final static int OG_MASTERMIND_OPTION = 1;

    // Opção para o jogo Bulls and Cows.
    final static int BULLS_AND_COWS_OPTION = 2;

    /**
     * Retorna as letras válidas para as tentativas do jogador.
     *
     * @param option A opção do jogo (1 para Mastermind, 2 para Bulls and Cows).
     * @return Uma string representando os caracteres válidos para o jogo selecionado.
     */
    public static String validLetters(int option) {
        StringBuilder result = new StringBuilder("[");
		
        // Adiciona as cores válidas dependendo do tipo de jogo.
        if (option == OG_MASTERMIND_OPTION) 
            for (MultiColour m : MultiColour.values()) 
                result.append(m.toString() + ", ");	
		
        else 
            for (BinaryColour m : BinaryColour.values()) 
                result.append(m.toString() + ", ");	
		
        // Remove a vírgula extra e fecha os colchetes.
        result.delete(result.length() - 2, result.length());
        result.append("]");
		
        return result.toString();
    }

    /**
     * Verifica se uma cor fornecida pelo jogador é válida.
     *
     * @param colour O caractere representando a cor.
     * @param gameOption A opção do jogo (1 para Mastermind, 2 para Bulls and Cows).
     * @return true se a cor for válida, false caso contrário.
     */
    public static boolean isValidColourFromUserTrial(char colour, int gameOption) {
        if (gameOption == OG_MASTERMIND_OPTION) 
            return MultiColour.fromChar(Character.toUpperCase(colour)) != null;
        else 
            return BinaryColour.fromChar(Character.toUpperCase(colour)) != null;
    }

    public static void main(String[] args) {

        // Inicializa o scanner para entrada do usuário.
        Scanner sc = new Scanner(System.in);

        // Inicializa o gerador de números aleatórios.
        Random random = new Random();

        // Opção escolhida pelo usuário.
        int option = 0;

        // Exibe o menu principal do jogo.
        System.out.println("Welcome to the Mastermind game!\n\nTo play, please choose an option.\n");

        // Valida a entrada do jogador até que uma opção válida seja selecionada.
        while (option != OG_MASTERMIND_OPTION && option != BULLS_AND_COWS_OPTION && option != 3) {
            System.out.print("1-Original Mastermind\n2-Bulls and cows\n3-Exit\nOption: ");
            option = sc.nextInt();
            sc.nextLine();
	        
            if (option != OG_MASTERMIND_OPTION && option != BULLS_AND_COWS_OPTION && option != 3) {
                System.out.printf("\n--------------------\nInvalid option!\n\n");
            }
        }
        
        // Se o jogador escolher sair, o programa termina.
        if (option != 3) {
            String userTrialInput; // Armazena as tentativas do usuário.
            AbstractMastermindGame game = null; // Instância do jogo.
            Colour[] colours = null; // Cores possíveis para o jogo.

            // Configura o jogo de acordo com a opção escolhida.
            switch (option) {
                case OG_MASTERMIND_OPTION:
                    colours = MultiColour.values();
                    game = new MultiColourMastermindGame(random.nextInt(), codeSize, colours);
                    break;
                case BULLS_AND_COWS_OPTION:
                    colours = BinaryColour.values();
                    game = new BullsAndCows(random.nextInt(), codeSize, colours);
                    break;
            }
        	
            char anotherRound = 'n'; // Controle para jogar outra rodada.

            // Loop do jogo principal.
            do {
                // Enquanto a rodada atual não terminar.
                while (!game.isRoundFinished()) {
                    System.out.println("\n--------------------");
//                    System.out.println("Resposta: " + game.code.getCode()); // TODO: Remover essa linha após depuração.
                    System.out.println(game.toString());
		        	
                    // Solicita uma tentativa ou dica ao jogador.
                    System.out.print("Attempt " + validLetters(option) + " or Hint(.): ");
                    userTrialInput = sc.nextLine();
		        	
                    // Se o jogador pedir uma dica.
                    if (userTrialInput.charAt(0) == '.') 
                        System.out.println("Hint: " + game.hint()); 
		            else {
                        // Armazena as cores da tentativa do jogador.
                        List<Colour> c = new ArrayList<Colour>();

                        // Valida cada letra da tentativa.
                        for (int i = 0; i < codeSize; i++) {
                            Colour currentTrialColour = null; // Cor atual.
			        		
                            boolean validFirstTrial = true;

                            // Loop para validar a entrada do jogador.
                            while (userTrialInput.length() != codeSize || 
                                   !isValidColourFromUserTrial(userTrialInput.charAt(i), option)) {
                                if (userTrialInput.length() != codeSize)
                                    System.out.println("Your attempt must have " + codeSize + " letters!");
				        		
                                if (!isValidColourFromUserTrial(userTrialInput.charAt(i), option))
                                    System.out.println("Your attempt must have valid letters! " + validLetters(option));
				        		
                                System.out.print("Attempt (ABCD) or Hint(.): ");
                                userTrialInput = sc.nextLine();
				        		
                                validFirstTrial = false;
                            }
			        		
                            // Adiciona a cor validada à lista de tentativa.
                            if (validFirstTrial) {
                                if (option == OG_MASTERMIND_OPTION) 
                                    currentTrialColour = MultiColour.fromChar(Character.toUpperCase(userTrialInput.charAt(i)));	        					
                                else  
                                    currentTrialColour = BinaryColour.fromChar(Character.toUpperCase(userTrialInput.charAt(i)));	
			        			
                                c.add(currentTrialColour);		
                            } else {
                                i = -1; // Reinicia a validação.
                            }
                        }
			        	
                        // Cria um código com base na tentativa do jogador.
                        Code userTrialCode = null;
                        if (option == OG_MASTERMIND_OPTION) 
                            userTrialCode = new Code(c);
			        	else {
                            // TODO: Implementar a classe BullsAndCowsCode.
                        }
			        	// Realiza a jogada.
                        game.play(userTrialCode);
		            }	
		        }

                // Mensagem de conclusão e opção de jogar novamente.
                System.out.println("\nCongratulations, you cracked the code!\nWould you like to play again? (y/n) ");
                anotherRound = sc.next().charAt(0);
                sc.nextLine();
                game.startNewRound(); // Inicia uma nova rodada.
        	
            } while (Character.toUpperCase(anotherRound) == 'Y');
        }
    }
}
