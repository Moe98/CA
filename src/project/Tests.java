package project;

import java.util.Arrays;

public class Tests {
	static int testCount = 0;
	static int testsPassed = 0;

	public static void main(String[] args) throws Exception {
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("Test Add: " + (addTest() ? "Passed" : "Failed"));
//		testsPassed = addTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Sub: " + (subTest() ? "Passed" : "Failed"));
//		testsPassed = subTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Addi: " + (addiTest() ? "Passed" : "Failed"));
//		testsPassed = addiTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Mul: " + (mulTest() ? "Passed" : "Failed"));
//		testsPassed = mulTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Div: " + (divTest() ? "Passed" : "Failed"));
//		testsPassed = divTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Mod: " + (modTest() ? "Passed" : "Failed"));
//		testsPassed = modTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test CountOne: " + (countOneTest() ? "Passed" : "Failed"));
//		testsPassed = countOneTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test switchSign: " + (switchSignTest() ? "Passed" : "Failed"));
//		testsPassed = switchSignTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Power: " + (powerTest() ? "Passed" : "Failed"));
//		testsPassed = powerTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Absolute: " + (absoluteTest() ? "Passed" : "Failed"));
//		testsPassed = absoluteTest() ? testsPassed + 1 : testsPassed;
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("Test And: " + (andTest() ? "Passed" : "Failed"));
//		testsPassed = andTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Or: " + (orTest() ? "Passed" : "Failed"));
//		testsPassed = orTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Xor: " + (xorTest() ? "Passed" : "Failed"));
//		testsPassed = xorTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Nor: " + (norTest() ? "Passed" : "Failed"));
//		testsPassed = norTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Xnor: " + (xnorTest() ? "Passed" : "Failed"));
//		testsPassed = xnorTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Sll: " + (sllTest() ? "Passed" : "Failed"));
//		testsPassed = sllTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Slr: " + (slrTest() ? "Passed" : "Failed"));
//		testsPassed = slrTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Slt: " + (sltTest() ? "Passed" : "Failed"));
//		testsPassed = sltTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Not: " + (notTest() ? "Passed" : "Failed"));
//		testsPassed = notTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Jal: " + (jalTest() ? "Passed" : "Failed"));
//		testsPassed = jalTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Jr: " + (jrTest() ? "Passed" : "Failed"));
//		testsPassed = jrTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Beq: " + (beqTest() ? "Passed" : "Failed"));
//		testsPassed = beqTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test Bne: " + (bneTest() ? "Passed" : "Failed"));
//		testsPassed = bneTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test BranchOnEven: " + (branchOnEvenTest() ? "Passed" : "Failed"));
//		testsPassed = branchOnEvenTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test BranchOnAbsolute: " + (branchOnAbsoluteTest() ? "Passed" : "Failed"));
//		testsPassed = branchOnAbsoluteTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test BranchOnDivisible: " + (branchOnDivisibleTest() ? "Passed" : "Failed"));
//		testsPassed = branchOnDivisibleTest() ? testsPassed + 1 : testsPassed;
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("Test LoadWord: " + (loadWordTest() ? "Passed" : "Failed"));
//		testsPassed = loadWordTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test StoreWord: " + (storeWordTest() ? "Passed" : "Failed"));
//		testsPassed = storeWordTest() ? testsPassed + 1 : testsPassed;
		System.out.println("Test StoreAndSwap: " + (storeAndSwapTest() ? "Passed" : "Failed"));
//		testsPassed = storeAndSwapTest() ? testsPassed + 1 : testsPassed;
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("Tests passed: " + testsPassed + " out of: " + testCount);
		System.out.println("🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥");
	}

	static boolean addTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(3);
		dataPath.registers[1] = dataPath.toBooleanArray(2);
		Compiler.parse("add $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 5;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean subTest() throws Exception { // rs and rt are switched
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(5);
		dataPath.registers[1] = dataPath.toBooleanArray(2);
		Compiler.parse("sub $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == -3;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean addiTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(4);
		dataPath.registers[1] = dataPath.toBooleanArray(15);
		Compiler.parse("addi $s2 5 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 20;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean mulTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(4);
		dataPath.registers[1] = dataPath.toBooleanArray(15);
		Compiler.parse("mul $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 60;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean divTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(5);
		dataPath.registers[1] = dataPath.toBooleanArray(15);
		Compiler.parse("div $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 3;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean modTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(7);
		dataPath.registers[1] = dataPath.toBooleanArray(15);
		Compiler.parse("mod $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 1;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean countOneTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(7);
		dataPath.registers[1] = dataPath.toBooleanArray(15);
		Compiler.parse("countOne $s2 $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 3;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean switchSignTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(7);
		dataPath.registers[1] = dataPath.toBooleanArray(15);
		Compiler.parse("switchSign $s2 $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == -7;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean powerTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(2);
		dataPath.registers[1] = dataPath.toBooleanArray(3);
		Compiler.parse("power $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 9;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean absoluteTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(-2);
		dataPath.registers[1] = dataPath.toBooleanArray(-3);
		Compiler.parse("absolute $s2 $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 2;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean andTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(2);
		dataPath.registers[1] = dataPath.toBooleanArray(3);
		Compiler.parse("and $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 2;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean orTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(4);
		dataPath.registers[1] = dataPath.toBooleanArray(8);
		Compiler.parse("or $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 12;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean xorTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(5);
		dataPath.registers[1] = dataPath.toBooleanArray(7);
		Compiler.parse("xor $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 5;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean norTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(5);
		dataPath.registers[1] = dataPath.toBooleanArray(7);
		Compiler.parse("nor $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == -8;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean xnorTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(5);
		dataPath.registers[1] = dataPath.toBooleanArray(7);
		Compiler.parse("xnor $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == -3;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean sllTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(4);
		dataPath.registers[1] = dataPath.toBooleanArray(2);
		Compiler.parse("sll $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 32;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean slrTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(2);
		dataPath.registers[1] = dataPath.toBooleanArray(16);
		Compiler.parse("slr $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 4;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean sltTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(16);
		dataPath.registers[1] = dataPath.toBooleanArray(7);
		Compiler.parse("slt $s2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 1;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean notTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(16);
		dataPath.registers[1] = dataPath.toBooleanArray(7);
		Compiler.parse("not $s2 $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == -17;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean jalTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(2);
		dataPath.registers[1] = dataPath.toBooleanArray(7);
		Compiler.parse("jal $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
//		System.out.println("PC: " + dataPath.PC);
		boolean returnValue = dataPath.toInt(dataPath.registers[8]) == 1 && dataPath.PC == 2; // should it be 2 or 3?
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean jrTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(2);
		dataPath.registers[1] = dataPath.toBooleanArray(7);
		Compiler.parse("jr $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
		boolean returnValue = dataPath.PC == 2;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean beqTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(2);
		dataPath.registers[1] = dataPath.toBooleanArray(2);
		Compiler.parse("beq 3 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
		boolean returnValue = dataPath.PC == 4;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean bneTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(3);
		dataPath.registers[1] = dataPath.toBooleanArray(2);
		Compiler.parse("bne 2 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
		boolean returnValue = dataPath.PC == 3;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean branchOnEvenTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(2);
		dataPath.registers[1] = dataPath.toBooleanArray(2);
		Compiler.parse("branchOnEven 4 $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
		boolean returnValue = dataPath.PC == 5;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean branchOnAbsoluteTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(2);
		dataPath.registers[1] = dataPath.toBooleanArray(-2);
		Compiler.parse("branchOnAbsolute 4 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
		boolean returnValue = dataPath.PC == 5;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean branchOnDivisibleTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(4);
		dataPath.registers[1] = dataPath.toBooleanArray(4);
		Compiler.parse("branchOnDivisible 4 $s0 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
		boolean returnValue = dataPath.PC == 5;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean loadWordTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.dataMemory[9] = dataPath.toBooleanArray(11);
		dataPath.registers[0] = dataPath.toBooleanArray(8);
		dataPath.dataMemory[7] = dataPath.toBooleanArray(3);
		dataPath.registers[1] = dataPath.toBooleanArray(9);
		Compiler.parse("loadWord $s2 1 $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
//			System.out.println(Arrays.toString(Compiler.commandsList.get(i)));
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
//		System.out.println("Register: " + dataPath.toInt(dataPath.registers[2]));
		boolean returnValue = dataPath.toInt(dataPath.registers[2]) == 11;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean storeWordTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(0);
		dataPath.registers[1] = dataPath.toBooleanArray(9);
		dataPath.registers[3] = dataPath.toBooleanArray(5);
		Compiler.parse("storeWord $s3 1 $s0\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
		boolean returnValue = dataPath.toInt(dataPath.dataMemory[1]) == 5;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;
	}

	static boolean storeAndSwapTest() throws Exception {
		testCount++;
		dataPath.PC = 0;
		dataPath.registers[0] = dataPath.toBooleanArray(7);
		dataPath.registers[1] = dataPath.toBooleanArray(1);
		dataPath.dataMemory[2] = dataPath.toBooleanArray(8);
		Compiler.parse("storeAndSwap $s0 1 $s1\n");
		for (int i = Compiler.commandsList.size() - 1; i >= 0; i--) {
			dataPath.instructionMemory[i] = Compiler.commandsList.remove(i);
			dataPath.run1Cycle();
		}
//		System.out.println(dataPath.PC);
		boolean returnValue = dataPath.toInt(dataPath.registers[0]) == 8 && dataPath.toInt(dataPath.dataMemory[2]) == 7;
		testsPassed = returnValue ? testsPassed + 1 : testsPassed;
		return returnValue;

	}
}