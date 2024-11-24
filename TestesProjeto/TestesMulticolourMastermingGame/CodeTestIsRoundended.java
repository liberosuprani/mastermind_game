package TestesMulticolourMastermingGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import types.MultiColour;
import types.MultiColourMastermindGame;
import types.Code;
import types.MastermingGame;

class CodeTestIsRoundended {

	public static String EOL = System.lineSeparator();

	private MultiColourMastermindGame jogo;

	@Test
	void teste1() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, binario);

		boolean expected = false;

		boolean actual = jogo.isRoundFinished();

		assertEquals(expected, actual);


	}


	@Test
	void teste2() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, binario);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);

		boolean expected = false;

		boolean actual = jogo.isRoundFinished();

		assertEquals(expected, actual);
	}
	
	

	@Test
	void teste3() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, binario);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.PINK);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);
		jogo.play(tentativa);

		boolean expected = false;

		boolean actual = jogo.isRoundFinished();

		assertEquals(expected, actual);
	}

	@Test
	void teste4() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, binario);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.GREEN);
		trial.add(MultiColour.GREEN);
		trial.add(MultiColour.GREEN);
		trial.add(MultiColour.GREEN);
		trial.add(MultiColour.GREEN);
		trial.add(MultiColour.GREEN);

		Code tentativa = new Code(trial);
		jogo.play(tentativa);

		trial.removeLast();
		trial.add(MultiColour.ORANGE);

		tentativa = new Code(trial);

		jogo.play(tentativa);

		boolean expected = false;

		boolean actual = jogo.isRoundFinished();

		assertEquals(expected, actual);;
	}

	@Test
	void teste5() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, binario);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.PINK);
		trial.add(MultiColour.RED);
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.ORANGE);
		trial.add(MultiColour.BLUE);


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

		boolean expected = true;

		boolean actual = jogo.isRoundFinished();

		assertEquals(expected, actual);
	}
	
	@Test
	void teste6() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, binario);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		
		for (int i = 1; i < MastermingGame.MAX_TRIALS; i++) {
			jogo.play(tentativa);
		}
		

		boolean expected = false;

		boolean actual = jogo.isRoundFinished();

		assertEquals(expected, actual);
	}
	
	@Test
	void teste7() {

		MultiColour[] binario = MultiColour.values();

		this.jogo = new MultiColourMastermindGame(0, 6, binario);

		ArrayList<MultiColour> trial = new ArrayList<MultiColour>();

		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);
		trial.add(MultiColour.BLUE);

		Code tentativa = new Code(trial);
		
		for (int i = 1; i <= MastermingGame.MAX_TRIALS; i++) {
			jogo.play(tentativa);
		}
		

		boolean expected = true;

		boolean actual = jogo.isRoundFinished();

		assertEquals(expected, actual);
	}
}



