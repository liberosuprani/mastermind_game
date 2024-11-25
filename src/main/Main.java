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
	
	final static int codeSize = 4;
	
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        int option = 0;
        System.out.println("Welcome to the Mastermind game!\n\nTo play, please choose an option.\n");

        while (option != 1 && option != 2 && option != 3) {
	        System.out.print("1-Original Mastermind\n2-Bulls and cows\n3-Exit\nOption: ");
	        option = sc.nextInt();
	        sc.nextLine();
	        
	        if (option != 1 && option != 2 && option != 3) {
	        	System.out.printf("\n--------------------\nInvalid option!\n\n");
	        }
        }
        
        if (option != 3) {
        	String userTrialInput;
        	AbstractMastermindGame game = null;
        	Colour[] colours = null;

        	switch (option) {
	        	case 1:
	        		colours = MultiColour.values();
	        		game = new MultiColourMastermindGame(random.nextInt(), codeSize, colours);
	        		break;
	        	case 2:
	        		colours = BinaryColour.values();
	        		game = new BullsAndCows(10000, codeSize, colours);
	        		break;
	        }
        	
        	char anotherRound = 'n';
        	
        	do {
	        	while (!game.isRoundFinished()) {
		        	System.out.println("\n--------------------");
		        	System.out.println("Resposta: " + game.code.getCode()); //TODO remover isso aqui, só adicionei pra fazer debug
		        	System.out.println(game.toString());
		        	
		        	System.out.print("Attempt (ABCD) or Hint(.): ");
		        	userTrialInput = sc.nextLine();
		        	
		        	while (userTrialInput.length() != codeSize && userTrialInput.charAt(0) != '.') {
		        		System.out.println("Your attempt must have " + codeSize + " letters!");
		        		System.out.print("Attempt (ABCD) or Hint(.): ");
		        		userTrialInput = sc.nextLine();
		        	}
		        	
		        	if (userTrialInput.charAt(0) == '.') {
		        		System.out.println("Hint: " + game.hint()); 
		        		
		        	}
		        	else {
			        	List<Colour> c = new ArrayList<Colour>();
			        	for (int i = 0; i < codeSize; i++) {
			        		if (option == 1) {
			        			//TODO tratar exceção de um char não existir nesses enums 
			        			c.add(MultiColour.fromChar(Character.toUpperCase(userTrialInput.charAt(i))));
			        		}
		        			else {
		        				c.add(BinaryColour.fromChar(Character.toUpperCase(userTrialInput.charAt(i))));
			        		}
			        		
			        	}
			        	
			        	Code userTrialCode = null;
			        	if (option == 1) {
			        		userTrialCode = new Code(c);
		        		}
		    			else {
		    				// userTrialCode = new BullsAndCowsCode();
		    				//TODO ainda falta implementar a classe BullsAndCowsCode
		        		}
			        	
			        	game.play(userTrialCode);
		        	}
		        }
	        	System.out.println("\nCongratulations, you cracked the code!\nWould you like to play again? (y/n) ");
	        	anotherRound = sc.next().charAt(0);
	        	sc.nextLine();
	        	game.startNewRound();
        	}
        	while (anotherRound == 'y');
	        
        }
    }

}
