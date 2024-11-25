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
	final static int OG_MASTERMIND_OPTION = 1;
	final static int BULLS_AND_COWS_OPTION = 2;
	
	public static String validLetters(int option) {
		
		StringBuilder result = new StringBuilder("[");
		
		if (option == OG_MASTERMIND_OPTION) 
			for (MultiColour m : MultiColour.values()) 
				result.append(m.toString() + ", ");	
		
		else 
			for (BinaryColour m : BinaryColour.values()) 
				result.append(m.toString() + ", ");	
		
		result.delete(result.length()-2, result.length());
		result.append("]");
		
		return result.toString();
	}
	
	public static boolean isValidColourFromUserTrial(char colour, int gameOption) {
		if (gameOption == OG_MASTERMIND_OPTION) {
			if (MultiColour.fromChar(Character.toUpperCase(colour)) == null)
				return false;
			return true;
		}
		else {
			if (BinaryColour.fromChar(Character.toUpperCase(colour)) == null)
				return false;
			return true;
		}
	}
	
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        int option = 0;
        System.out.println("Welcome to the Mastermind game!\n\nTo play, please choose an option.\n");

        while (option != OG_MASTERMIND_OPTION && option != BULLS_AND_COWS_OPTION && option != 3) {
	        System.out.print("1-Original Mastermind\n2-Bulls and cows\n3-Exit\nOption: ");
	        option = sc.nextInt();
	        sc.nextLine();
	        
	        if (option != OG_MASTERMIND_OPTION && option != BULLS_AND_COWS_OPTION && option != 3) {
	        	System.out.printf("\n--------------------\nInvalid option!\n\n");
	        }
        }
        
        if (option != 3) {
        	String userTrialInput;
        	AbstractMastermindGame game = null;
        	Colour[] colours = null;

        	switch (option) {
	        	case OG_MASTERMIND_OPTION:
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
		        	
		        	if (userTrialInput.charAt(0) == '.') 
		        		System.out.println("Hint: " + game.hint()); 
		        
		        	else {
			        	List<Colour> c = new ArrayList<Colour>();
			        	for (int i = 0; i < codeSize; i++) {
			        		Colour currentTrialColour = null;
			        		
			        		//TODO tem que acertar isso aqui
			        		while (userTrialInput.length() != codeSize && isValidColourFromUserTrial(userTrialInput.charAt(i), option)) {
				        		if (userTrialInput.length() != codeSize)
				        			System.out.println("Your attempt must have " + codeSize + " letters!");
				        		if (isValidColourFromUserTrial(userTrialInput.charAt(i), option))
				        			System.out.println("Your attempt must have valid letters " + validLetters(option));
				        		System.out.print("Attempt (ABCD) or Hint(.): ");
				        		userTrialInput = sc.nextLine();
				        	}
			        		
		        			if (option == OG_MASTERMIND_OPTION) 
		        				currentTrialColour = MultiColour.fromChar(Character.toUpperCase(userTrialInput.charAt(i)));	        					
		        			else  
		        				currentTrialColour = BinaryColour.fromChar(Character.toUpperCase(userTrialInput.charAt(i)));	
		        			
		        			c.add(currentTrialColour);		
		          		}
			        	
			        	Code userTrialCode = null;
			        	if (option == OG_MASTERMIND_OPTION) 
			        		userTrialCode = new Code(c);
			        	
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
	        	
        	}while (anotherRound == 'y');
    	
        }
    }
}
