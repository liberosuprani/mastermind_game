package tests.testesCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import types.BinaryColour;
import types.Code;
import types.MultiColour;

class CodeTestEquals {

	private Code codeToTest1;
	private Code codeToTest2;

	@Test
	void teste1() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start);

		boolean expected = true;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);


	}
	
	@Test
	void teste2() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);

		boolean expected = false;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);


	}

	@Test
	void teste3() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		start.removeLast();
		start.add(MultiColour.ORANGE);
		
		codeToTest2 = new Code(start);
		
		boolean expected = false;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);
	}
	
	@Test
	void teste4() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		start.add(MultiColour.BLUE);
		
		codeToTest2 = new Code(start);

		boolean expected = false;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);


	}
	
	@Test
	void teste5() {

		ArrayList<BinaryColour> start = new ArrayList<BinaryColour>();

		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.WHITE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start);

		boolean expected = true;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);
	}
	
	@Test
	void teste6() {

		ArrayList<BinaryColour> start = new ArrayList<BinaryColour>();

		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.WHITE);
		
		codeToTest1 = new Code(start);
		
		start.add(BinaryColour.WHITE);
		codeToTest2 = new Code(start);
		

		boolean expected = false;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);


	}

}
