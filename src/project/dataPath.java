package project;

public class dataPath {
	static final int instructionMemorySize = 1024;
	static final int dataMemorySize = 1024;
	static final int sizeOfData = 32;
	static final int numberOfRegisters = 15;
	
	static int PC = 0;
	static boolean[][] instructionMemory = new boolean[instructionMemorySize][18];
	static boolean[][] dataMemory = new boolean[dataMemorySize][sizeOfData];
	static boolean[][] registers = new boolean [numberOfRegisters][sizeOfData];
	public static void main(String[] args) {
	}

	static boolean[] ALU(boolean[] readData1, boolean[] readData2, boolean[] ALU_Control) {
		boolean[] ALU_Result = new boolean[sizeOfData];
		// TODO implement ALU logic
		return ALU_Result;
	}

	static int ADD4(int PC) {
		return PC + 4;
	}

	static int ADD(boolean[] branchAddress, int PC_Added4) {
		return toInt(branchAddress) + PC_Added4;
	}

	static boolean[] signExtent(boolean[] bits) {
		boolean[] extendedBits = new boolean[sizeOfData];
		for (int i = 0; i < sizeOfData; i++)
			extendedBits[i] = i < bits.length ? bits[i] : bits[sizeOfData - 1];
		return extendedBits;
	}

	static ControlSignals Control(boolean[] opcode) {
		ControlSignals signals = new ControlSignals();
		// TODO implemnt Control logic
		return signals;
	}

	static boolean[] ALU_Control(boolean[] funct, boolean ALUOp) {
		// TODO implement ALU_Control Logic
		return new boolean[0];
	}

	static boolean[] Mux(boolean[] a, boolean[] b, boolean controlSignal) {
		return controlSignal ? b : a;
	}

	static boolean[] shiftLeft2(boolean[] bits) {
		boolean[] shiftedBits = new boolean[bits.length];
		for (int i = 0; i < bits.length; i++)
			shiftedBits[i] = bits[(i + 2) % bits.length];
		return shiftedBits;
	}

	static boolean AND(boolean a, boolean b) {
		return a && b;
	}

	static boolean[] dataMemory(boolean[] Address, boolean[] writeData, boolean MemRead, boolean MemWrite) {
		int intAddress = toInt(Address);
		if (MemWrite)
			dataMemory[intAddress] = writeData;
		return dataMemory[intAddress];
	}

	static boolean[][] Registers(boolean[] readRegister1, boolean[] readRegister2, boolean[] writeRegister,
	boolean[] writeData, boolean regWrite) {
		/*
		 * returns an Array (length 2) of bits(booleans) position 0 is ReadData1
		 * position 1 is ReadData2
		 */
		boolean[] readData1 = registers[toInt(readRegister1)];
		boolean[] readData2 = registers[toInt(readRegister2)];
		
		if(regWrite)
			registers[toInt(writeRegister)]=writeData;
		
		return new boolean[][] { readData1, readData2 };
	}

	// Helpers (Might need them for simpler code but not required)
	static int toInt(boolean[] bits) {
		int sol = 0;
		for (int i = 0; i < bits.length; i++)
			sol = (sol << 1) + (bits[i] ? 1 : 0);
		return sol;
	}

	static boolean[] toBooleanArray(int num) {
		boolean[] bools = new boolean[sizeOfData];
		for (int i = 0; i < sizeOfData; i++)
			bools[i] = (num & (1 << i)) != 0;
		return bools;
	}
}
