package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import types.*;

public class Main {
	
    final static int codeSize = 4;

    // opção para o jogo Mastermind Original.
    final static int OG_MASTERMIND_OPTION = 1;

    // opção para o jogo Bulls and Cows.
    final static int BULLS_AND_COWS_OPTION = 2;

    /**
     * Retorna as letras válidas para as tentativas do jogador.
     *
     * @param option - opção do jogo (1 para Mastermind, 2 para Bulls and Cows).
     * @return String que representa os caracteres válidos para o jogo selecionado.
     */
    public static String validLetters(int option) {
        StringBuilder result = new StringBuilder("[");
		
        // adiciona as cores válidas a depender do tipo de jogo.
        if (option == OG_MASTERMIND_OPTION) 
            for (MultiColour m : MultiColour.values()) 
                result.append(m.toString() + ", ");	
		
        else 
            for (BinaryColour m : BinaryColour.values()) 
                result.append(m.toString() + ", ");	
		
        // remove a vírgula extra e fecha as chavetas
        result.delete(result.length() - 2, result.length());
        result.append("]");
		
        return result.toString();
    }

    /**
     * Verifica se uma cor fornecida pelo jogador é válida.
     *
     * @param colour O caracter que representa a cor.
     * @param gameOption Opção do jogo (1 para Mastermind, 2 para Bulls and Cows).
     * @return true se a cor for válida, false caso contrário.
     */
    public static boolean isValidColourFromUserTrial(char colour, int gameOption) {
        if (gameOption == OG_MASTERMIND_OPTION) 
            return MultiColour.fromChar(Character.toUpperCase(colour)) != null;
        else 
            return BinaryColour.fromChar(Character.toUpperCase(colour)) != null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Random random = new Random();

        int option = 0;

        System.out.println("Welcome to the Mastermind game!\n\nTo play, please choose an option.\n");

        // valida a entrada do jogador até que uma opção válida seja selecionada
        while (option != OG_MASTERMIND_OPTION && option != BULLS_AND_COWS_OPTION && option != 3) {
            System.out.print("1-Original Mastermind\n2-Bulls and cows\n3-Exit\nOption: ");
            option = sc.nextInt();
            sc.nextLine();
	        
            if (option != OG_MASTERMIND_OPTION && option != BULLS_AND_COWS_OPTION && option != 3) {
                System.out.printf("\n--------------------\nInvalid option!\n\n");
            }
        }
        
        // se o jogador escolher 3, o programa termina
        if (option != 3) {
            String userTrialInput; // armazena as tentativas do usuário
            AbstractMastermindGame game = null; 
            Colour[] colours = null; // cores possíveis para o jogo.

            // configura o jogo de acordo com a opção escolhida
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
        	
            char anotherRound = 'n'; 

            do {
                while (!game.isRoundFinished()) {
                    System.out.println("\n--------------------");
                    System.out.println("Resposta: " + game.code.getCode()); // TODO: Remover essa linha após depuração.
                    System.out.println(game.toString());
		        	
                    System.out.print("Attempt " + validLetters(option) + " or Hint(.): ");
                    userTrialInput = sc.nextLine();
		        	
                    if (userTrialInput.charAt(0) == '.') 
                        System.out.println("Hint: " + game.hint()); 
		            else {
                        List<Colour> trialColoursList = new ArrayList<Colour>();

                        for (int i = 0; i < codeSize; i++) {
                            Colour currentTrialColour = null; // cor atual
			        		
                            boolean restartColoursLoop = false;

                            // loop para validar a entrada do jogador
                            while ( (userTrialInput.length() != codeSize || !isValidColourFromUserTrial(userTrialInput.charAt(i), option)) && !restartColoursLoop ) {
                                if (userTrialInput.length() != codeSize)
                                    System.out.println("Your attempt must have " + codeSize + " letters!");
				        		
                                if (!isValidColourFromUserTrial(userTrialInput.charAt(i), option))
                                    System.out.println("Your attempt must have valid letters! " + validLetters(option));
				        		
                                System.out.print("Attempt " + validLetters(option) + " or Hint(.): ");
                                userTrialInput = sc.nextLine();
				        		
                                restartColoursLoop = true;
                                trialColoursList.clear();
                            }
			        		
                            // adiciona a cor validada à lista das cores da tentativa
                            if (!restartColoursLoop) {
                                if (option == OG_MASTERMIND_OPTION) 
                                    currentTrialColour = MultiColour.fromChar(Character.toUpperCase(userTrialInput.charAt(i)));	        					
                                else  
                                    currentTrialColour = BinaryColour.fromChar(Character.toUpperCase(userTrialInput.charAt(i)));	
			        			
                                trialColoursList.add(currentTrialColour);		
                            } else 
                                i = -1; // Reinicia a validação.
                            
                        }
			        	
                        // cria um código com base na tentativa do jogador
                        Code userTrialCode = null;
                        if (option == OG_MASTERMIND_OPTION) 
                            userTrialCode = new Code(trialColoursList);
			        	else {
                            List<BinaryColour> trialBinaryColoursList = new ArrayList<BinaryColour>();

                            for (Colour c : trialColoursList) 
                                trialBinaryColoursList.add((BinaryColour) c);
                            
                            userTrialCode = new BullsAndCowsCode(trialBinaryColoursList);
                        }
                        game.play(userTrialCode);
		            }	
		        }

                System.out.print("\nCongratulations, you cracked the code!\nWould you like to play again? (y/n) ");
                anotherRound = sc.next().charAt(0);
                sc.nextLine();
                game.startNewRound(); // inicia uma nova rodada
        	
            } while (Character.toUpperCase(anotherRound) == 'Y');
        }
    }
}
