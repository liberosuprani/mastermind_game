package TestesMulticolourMastermingGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import types.MultiColour;
import types.MultiColourMastermindGame;
import types.Code;

class CodeTestToSTring {

	public static String EOL = System.lineSeparator();

	private MultiColourMastermindGame jogo;

	@Test
	void teste1() {

		MultiColour[] multicolour = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 4, multicolour);

		String expected = "Number of Trials = 0" + EOL
				+ "Score = 0" + EOL
				+ "[?, ?, ?, ?]" + EOL
				+ EOL;
		String actual = jogo.toString();

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

		String expected = "Number of Trials = 1" + EOL
				+ "Score = 0" + EOL
				+ "[?, ?, ?, ?]" + EOL
				+ "\n"
				+ "[B, B, B, B]    1 0" + EOL;
		String actual = jogo.toString();

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

		String expected = "Number of Trials = 2" + EOL
				+ "Score = 0" + EOL
				+ "[?, ?, ?, ?]" + EOL
				+ "\n"
				+ "[B, B, B, B]    1 0" + EOL;		
		String actual = jogo.toString();

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

		String expected = "Number of Trials = 3" + EOL
				+ "Score = 0" + EOL
				+ "[?, ?, ?, ?]" + EOL
				+ "\n"
				+ "[B, B, B, B]    1 0" + EOL
				+ "[B, B, B, O]    2 0" + EOL;
		String actual = jogo.toString();

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

		String expected = "Number of Trials = 4" + EOL
				+ "Score = 50" + EOL
				+ "[B, P, R, O]" + EOL
				+ "\n"
				+ "[B, B, B, B]    1 0" + EOL
				+ "[B, B, B, O]    2 0" + EOL
				+ "[B, P, R, O]    4 0" + EOL;
		String actual = jogo.toString();

		assertEquals(expected, actual);
	}
}



