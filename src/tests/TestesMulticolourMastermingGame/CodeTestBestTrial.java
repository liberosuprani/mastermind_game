package tests.TestesMulticolourMastermingGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import types.MultiColour;
import types.MultiColourMastermindGame;
import types.Code;

class CodeTestBestTrial {

	public static String EOL = System.lineSeparator();

	private MultiColourMastermindGame jogo;

	@Test
	void teste1() {
		
		MultiColour[] multicolour = MultiColour.values();
		
		this.jogo = new MultiColourMastermindGame(0, 6, multicolour);
		
		Code expected = null;
		
		Code actual = jogo.bestTrial();
		
		assertEquals(expected, actual);
	}

	@Test
	void teste2() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, multicolour);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);

		Code expected = tentativa;

		Code actual = jogo.bestTrial();

		assertEquals(expected, actual);

	}

	
	@Test
	void teste3() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, multicolour);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.ORANGE);

		Code tentativa = new Code(trial);
		
		Code expected = tentativa;
		
		jogo.play(tentativa);
		jogo.play(tentativa);

		trial.removeLast();
		trial.add(MultiColour.BLUE);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		Code actual = jogo.bestTrial();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void teste4() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, multicolour);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.ORANGE);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);
		jogo.play(tentativa);

		trial.removeLast();
		trial.add(MultiColour.RED);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		trial.clear();
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.PINK);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.ORANGE);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		
		Code expected = tentativa;

		Code actual = jogo.bestTrial();

		assertEquals(expected, actual);
	}
	
	@Test
	void teste5() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, multicolour);
		
		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		// BPROOO
		
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.PINK);	// BBBPOO -> 3 1
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.ORANGE);

		Code tentativa = new Code(trial);
				
		jogo.play(tentativa);
		
		Code expected = tentativa;
		
		trial.clear();
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);	// BBBBOO -> 3 0
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.ORANGE);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		Code actual = jogo.bestTrial();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void teste6() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, multicolour);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);

		Code tentativa = new Code(trial);
				
		jogo.play(tentativa);
		
		
		
		trial.clear();
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);

		tentativa = new Code(trial);

		jogo.play(tentativa);
		
		Code expected = tentativa;
		Code actual = jogo.bestTrial();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void teste7() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(1, 6, multicolour);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.GREEN);

		Code tentativa = new Code(trial);
				
		jogo.play(tentativa);
		
		
		trial.clear();
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.GREEN);
		trial.add(MultiColour.RED);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		Code expected = tentativa;
				
		Code actual = jogo.bestTrial();
		
		assertEquals(expected, actual);
	}
}



