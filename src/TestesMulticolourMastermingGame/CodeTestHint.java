package TestesMulticolourMastermingGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import types.MultiColour;
import types.MultiColourMastermindGame;
import types.Code;
import types.Colour;

class CodeTestHint {

	public static String EOL = System.lineSeparator();

	private MultiColourMastermindGame jogo;

	@Test
	void teste1() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, binario);

		Colour expected = MultiColour.RED;
		Colour actual = jogo.hint();

		assertEquals(expected, actual);
	}
	
	
	@Test
	void teste2() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(2, 4, binario);

		Colour expected = MultiColour.BLUE;
		Colour actual = jogo.hint();

		assertEquals(expected, actual);


	}
	
	@Test
	void teste3() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(2, 6, binario);

		jogo.hint();
		
		int expected = 0;
		int actual = jogo.score();

		assertEquals(expected, actual);
	}
	
	@Test
	void teste5() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(2, 6, binario);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.PINK);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.YELLOW);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.GREEN);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);
		jogo.play(tentativa);

		trial.removeLast();
		trial.add(MultiColour.ORANGE);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		
		jogo.startNewRound();
		
		jogo.hint();

		int expected = 150;
		
		trial.clear();
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.GREEN);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.YELLOW);
		trial.add(MultiColour.YELLOW);
		
		tentativa = new Code(trial);
		
		jogo.play(tentativa);
		

		int actual = jogo.score();

		assertEquals(expected, actual);
	}



}



