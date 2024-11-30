package tests.testesCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import types.BinaryColour;
import types.BullsAndCowsCode;
import types.Code;
import types.MultiColour;

class CodeTestClone {

	private Code codeToTest1;
	private Code codeToTest2;

	@Test
	void teste1() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = codeToTest1.clone();

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
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = codeToTest1.clone();

		boolean expected = true;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);



	}
	
	
	@Test
	void teste3() {

		ArrayList<BinaryColour> start = new ArrayList<BinaryColour>();

		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.WHITE);
		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.BLACK);
		
		codeToTest1 = new Code(start);
		codeToTest2 = codeToTest1.clone();

		boolean expected = true;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);



	}
	
	@Test
	void teste4() {

		ArrayList<BinaryColour> start = new ArrayList<BinaryColour>();

		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.WHITE);

		
		codeToTest1 = new Code(start);
		codeToTest2 = codeToTest1.clone();

		boolean expected = true;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);

	}
	
	@Test
	void teste5() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		
		codeToTest1 = new Code(start);
		codeToTest2 = codeToTest1.clone();

		boolean expected = true;
		boolean actual = codeToTest1.equals(codeToTest2);

		assertEquals(actual, expected);
	}
	
	@Test
	public void teste6() {
		
		assertThrows(
					NullPointerException.class,
						() -> codeToTest1.clone(),
						"Expected top() to throw, but it didn't"
		);
	}
	
	@Test
	public void teste7() {
		
		ArrayList<BinaryColour> start = new ArrayList<BinaryColour>();
		
		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.WHITE);

		BullsAndCowsCode codeToTest3 = new BullsAndCowsCode(start);
		BullsAndCowsCode codeToTest4 = codeToTest3.clone();

		boolean expected = true;
		boolean actual = codeToTest3.equals(codeToTest4);

		assertEquals(actual, expected);
	}



}
