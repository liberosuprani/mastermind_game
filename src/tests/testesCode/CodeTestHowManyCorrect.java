package tests.testesCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import types.BinaryColour;
import types.Code;
import types.MultiColour;
import types.BullsAndCowsCode;

class CodeTestHowManyCorrect {

	private Code codeToTest1;
	private Code codeToTest2;

	@Test
	void teste1() {

		ArrayList<BinaryColour> start = new ArrayList<BinaryColour>();

		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.WHITE);
		start.add(BinaryColour.WHITE);
		
		codeToTest1 = new Code(start);
		
		ArrayList<BinaryColour> start2 = new ArrayList<BinaryColour>();

		start2.add(BinaryColour.BLACK);
		start2.add(BinaryColour.BLACK);
		start2.add(BinaryColour.WHITE);
		start2.add(BinaryColour.WHITE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {4, 0};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);
	}
	
	@Test
	void teste2() {

		ArrayList<BinaryColour> start = new ArrayList<BinaryColour>();

		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.BLACK);
		
		codeToTest1 = new Code(start);
		
		ArrayList<BinaryColour> start2 = new ArrayList<BinaryColour>();

		start2.add(BinaryColour.WHITE);
		start2.add(BinaryColour.WHITE);
		start2.add(BinaryColour.WHITE);
		start2.add(BinaryColour.WHITE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {0, 0};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);


	}

	
	
	@Test
	void teste3() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		ArrayList<MultiColour> start2 = new ArrayList<MultiColour>();

		start2.add(MultiColour.BLUE);
		start2.add(MultiColour.BLUE);
		start2.add(MultiColour.BLUE);
		start2.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {4, 0};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);


	}
	
	@Test
	void teste4() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		ArrayList<MultiColour> start2 = new ArrayList<MultiColour>();

		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.GREEN);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {0, 0};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);


	}
	
	@Test
	void teste5() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		ArrayList<MultiColour> start2 = new ArrayList<MultiColour>();

		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.GREEN);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {1, 0};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);


	}
	
	@Test
	void teste6() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		ArrayList<MultiColour> start2 = new ArrayList<MultiColour>();

		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.BLUE);
		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.GREEN);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {0, 2};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);


	}
	
	@Test
	void teste7() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.ORANGE);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		ArrayList<MultiColour> start2 = new ArrayList<MultiColour>();

		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.BLUE);
		start2.add(MultiColour.ORANGE);
		start2.add(MultiColour.GREEN);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {1, 2};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);

	}
	
	@Test
	void teste8() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.ORANGE);
		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		ArrayList<MultiColour> start2 = new ArrayList<MultiColour>();

		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.BLUE);
		start2.add(MultiColour.ORANGE);
		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.ORANGE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {1, 2};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);


	}
	
	@Test
	void teste9() {

		ArrayList<MultiColour> start = new ArrayList<MultiColour>();

		start.add(MultiColour.BLUE);
		start.add(MultiColour.GREEN);
		start.add(MultiColour.ORANGE);
		start.add(MultiColour.BLUE);
		start.add(MultiColour.BLUE);
		
		codeToTest1 = new Code(start);
		
		ArrayList<MultiColour> start2 = new ArrayList<MultiColour>();

		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.BLUE);
		start2.add(MultiColour.ORANGE);
		start2.add(MultiColour.GREEN);
		start2.add(MultiColour.ORANGE);
		
		codeToTest1 = new Code(start);
		codeToTest2 = new Code(start2);

        int[] expected = {1, 2};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);


	}
	
	@Test
	void teste10() {

		ArrayList<BinaryColour> start = new ArrayList<BinaryColour>();

		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.BLACK);
		start.add(BinaryColour.WHITE);
		start.add(BinaryColour.WHITE);
		
		codeToTest1 = new BullsAndCowsCode(start);
		
		ArrayList<BinaryColour> start2 = new ArrayList<BinaryColour>();

		start2.add(BinaryColour.WHITE);
		start2.add(BinaryColour.BLACK);
		start2.add(BinaryColour.WHITE);
		start2.add(BinaryColour.BLACK);
		
		codeToTest2 = new BullsAndCowsCode(start2);

        int[] expected = {2, 2};
        int[] result = codeToTest1.howManyCorrect(codeToTest2);

        assertArrayEquals(expected, result);
	}
}

