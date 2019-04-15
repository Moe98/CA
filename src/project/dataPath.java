package project;

import java.util.Arrays;

public class dataPath {
	static final int instructionMemorySize = 1024;
	static final int dataMemorySize = 1024;
	static final int sizeOfData = 32;
	static final int numberOfRegisters = 15;

	static int PC = 0;
	static boolean[][] instructionMemory = new boolean[instructionMemorySize][18];
	static boolean[][] dataMemory = new boolean[dataMemorySize][sizeOfData];
	static boolean[][] registers = new boolean [numberOfRegisters][sizeOfData];
  
	public static void main(String[] args) throws Exception 
	{
		
		boolean [] instruction = instructionMemory[PC];
		boolean [] part0to7=Arrays.copyOfRange(instruction, 0, 7);
		boolean [] part0to3=Arrays.copyOfRange(part0to7, 0, 3);
		boolean [] part4to7=Arrays.copyOfRange(instruction, 4, 7);
		boolean [] part8to11=Arrays.copyOfRange(instruction, 8, 11);
		boolean [] part12to15=Arrays.copyOfRange(instruction, 12, 15);
		boolean [] part16to17=Arrays.copyOfRange(instruction, 16, 17);
		
		ControlSignals control=Control(part16to17,part0to3);
		
		
	}

	static boolean[] ALU(boolean[] readData1, boolean[] readData2, boolean[] ALU_Control) throws Exception {
		int control=toInt(ALU_Control);
		int data1=toInt(readData1);
		int data2=toInt(readData2);
		if(control==0)
			return toBooleanArray(data1+data2);
		else if(control==1)
			return toBooleanArray(data1-data2);
		else if(control==2)
			return toBooleanArray(data1*data2);
		else if(control==3)
			return toBooleanArray(data1/data2);
		else if(control==4)
			return toBooleanArray(data1%data2);
		else if(control==5)
			return toBooleanArray(Integer.bitCount(data1));//TODO check if it uses data1 or data2
		else if(control==6)
			return toBooleanArray(-1*data1);//TODO check if it uses data1 or data2
		else if(control==7)
			return toBooleanArray((int)Math.pow(data1, data2));
		else if(control==8)
			return toBooleanArray(Math.abs(data1));//TODO check if it uses data1 or data2
		else if(control==9)
		{
			boolean [] sol=new boolean[readData1.length];
			for(int i=0;i<readData1.length;i++)
				sol[i]=readData1[i]&readData2[i];
			return sol;
		}
		else if(control==10)
		{
			boolean [] sol=new boolean[readData1.length];
			for(int i=0;i<readData1.length;i++)
				sol[i]=readData1[i]|readData2[i];
			return sol;
		}
		else if(control==11)
		{
			boolean [] sol=new boolean[readData1.length];
			for(int i=0;i<readData1.length;i++)
				sol[i]=readData1[i]^readData2[i];
			return sol;
		}
		else if(control==12)
		{
			boolean [] sol=new boolean[readData1.length];
			for(int i=0;i<readData1.length;i++)
				sol[i]=!(readData1[i]|readData2[i]);
			return sol;
		}
		else if(control==13)
		{
			boolean [] sol=new boolean[readData1.length];
			for(int i=0;i<readData1.length;i++)
				sol[i]=!(readData1[i]^readData2[i]);
			return sol;
		}
		else if(control==14)
		{
			boolean [] sol=new boolean[readData1.length];
			for(int i=0;i<readData1.length;i++)
				sol[i] = readData1[(i + data2) % readData1.length];
			return sol;
		}
		else if(control==15)
		{
			boolean [] sol=new boolean[readData1.length];
			for(int i=0;i<readData1.length;i++)
				sol[i] = readData1[(i - data2) % readData1.length];
			return sol;
		}
		else if(control==16)
			return toBooleanArray(data1<data2 ? 1:0);
		else if(control==17)
		{
			boolean [] sol=new boolean[readData1.length];
			for(int i=0;i<readData1.length;i++)
				sol[i] = !readData1[i];
			return sol;
		}
		throw new Exception("Invalid Control Signal");
	}

	static int ADD4(int PC) {
		return PC + 4;
	}

	static boolean[] ADD(boolean[] branchAddress, int PC_Added4) {
		return toBooleanArray(toInt(branchAddress) + PC_Added4);
	}

	static boolean[] signExtent(boolean[] bits) {
		boolean[] extendedBits = new boolean[sizeOfData];
		for (int i = 0; i < sizeOfData; i++)
			extendedBits[i] = i < bits.length ? bits[i] : bits[sizeOfData - 1];
		return extendedBits;
	}

	static ControlSignals Control(boolean[] opcode ,boolean[] funct) {
		ControlSignals signals = null;
		if(opcode[0]&&opcode[1]) {
			//AR type 11
			boolean aluop [] = {true,true};
		    signals= new ControlSignals(true,  false, false, false, false, aluop, false, false, true);		
		}else if (opcode[0]&&!opcode[1]) {
			//LO type 10
			boolean aluop [] = {true,true};
		    signals= new ControlSignals(true,  false, false, false, false, aluop, false, false, true);		
			
		}else if (!opcode[0]&&opcode[1]) {
			//br type & j
			
			// in case of J
			if((!funct[0]&!funct[1]&!funct[2]&!funct[3])||(!funct[0]&!funct[1]&!funct[2]&funct[3]))
		    signals= new ControlSignals(false,  true, false, false, false, null, false, false, false);		
			else {
		    //in case of br
			boolean aluop [] = {true,true};
		    signals= new ControlSignals(false,  false, true, false, false, aluop, false, false, false);	
			}
			
		}else if (!opcode[0]&&!opcode[1]) {
			//load and sw
			if((!funct[0]&!funct[1]&!funct[2]&!funct[3])) {
				boolean aluop [] = {true,true};
			    signals=  new ControlSignals(false, false, false, true, true, aluop, false, true, true);		
			}else if((!funct[0]&!funct[1]&!funct[2]&funct[3])) {
				boolean aluop [] = {true,true};
			    signals=  new ControlSignals(false, false, false, false, false, aluop, true, true, false);	
			    
			}else{
				//load and swap ?!
			}
					    
		}
		return signals;
	}

	static boolean[] ALU_Control(boolean[] funct, boolean[] ALUOp) {
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
