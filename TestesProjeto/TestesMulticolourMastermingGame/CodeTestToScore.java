package TestesMulticolourMastermingGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import types.Code;
import types.MultiColour;
import types.MultiColourMastermindGame;

class CodeTestToScore {

	public static String EOL = System.lineSeparator();

	private MultiColourMastermindGame jogo;

	@Test
	void teste1() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, multicolour);

		int expected = 0;
		int actual = jogo.score();

		assertEquals(expected, actual);


	}


	@Test
	void teste2() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, multicolour);


		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);


		Code tentativa = new Code(trial);
		jogo.play(tentativa);

		int expected = 0;
		int actual = jogo.score();

		assertEquals(expected, actual);
	}

	@Test
	void teste3() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, multicolour);


		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
	

		Code tentativa = new Code(trial);
		jogo.play(tentativa);
		jogo.play(tentativa);

		int expected = 0;
		int actual = jogo.score();

		assertEquals(expected, actual);
	}

	@Test
	void teste4() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, multicolour);


		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);


		Code tentativa = new Code(trial);
		jogo.play(tentativa);
		jogo.play(tentativa);

		trial.removeLast();
		trial.add(MultiColour.ORANGE);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		jogo.play(tentativa);

		int expected = 0;
		int actual = jogo.score();

		assertEquals(expected, actual);
	}

	@Test
	void teste5() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, multicolour);


		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);
		jogo.play(tentativa);

		trial.removeLast();
		trial.add(MultiColour.ORANGE);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		trial.clear();
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.PINK);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.ORANGE);


		tentativa = new Code(trial);

		jogo.play(tentativa);

		int expected = 50;
		int actual = jogo.score();

		assertEquals(expected, actual);
	}
	
	
	@Test
	void teste6() {


		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, multicolour);


		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);

		trial.clear();
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.PINK);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.ORANGE);


		tentativa = new Code(trial);

		jogo.play(tentativa);

		int expected = 100;
		int actual = jogo.score();

		assertEquals(expected, actual);
	}
	
	@Test
	void teste7() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, multicolour);


		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);
		

		trial.removeLast();
		trial.add(MultiColour.ORANGE);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		trial.clear();
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.PINK);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.ORANGE);


		tentativa = new Code(trial);

		jogo.play(tentativa);

		int expected = 50;
		int actual = jogo.score();

		assertEquals(expected, actual);
	}
}



