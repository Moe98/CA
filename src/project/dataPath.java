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
	
		//store values in registers at address 2 and 3
		boolean [] value4 = flipIt(toBooleanArray(4));
	    boolean [] value5 = flipIt(toBooleanArray(5));
		registers[2]= value4;
		registers[3]= value5;
		
	    //add/sub what is stored in address 2 and 3 and put in address 0 
		String insADD = "110000001100100000";
		String insSUB = "110000001100100010";
		
		//put instruction in memory
		boolean instructionADD [] =stringInsToBool(insADD); 		
		instructionMemory[0] = flipIt(instructionADD);
		
		//get instruction from memory
		boolean [] instruction = instructionMemory[PC];
	
		boolean [] part0to3=Arrays.copyOfRange(instruction, 0, 4);
		boolean [] part16to17=Arrays.copyOfRange(instruction, 16, 18);
	
		//get control signals
		ControlSignals control=Control(part16to17,part0to3);
        
		//display your control signals (printing)
		displayControlSignals(control);
		
		//get into action
		arOP(control,instruction);
		
		//check result in memory register
		System.out.println("result "+toInt(registers[0]));
		
	}
	
	static void displayControlSignals(ControlSignals control) {
		
		System.out.println("RegDest "+control.RegDest);
		System.out.println("jump "+control.Jump);
		System.out.println("branch "+control.Branch);
		System.out.println("MemRead "+control.MemRead);
		System.out.println("MemToReg "+control.MemToReg);
		System.out.println("aluOP "+control.ALUOp[0]+" "+control.ALUOp[1]);
		System.out.println("MemWrite "+control.MemWrite);
		System.out.println("ALUSrc "+control.ALUSrc);
		System.out.println("RegWrite "+control.RegWrite);
		System.out.println();
		
	}
	
	static boolean [] stringInsToBool (String s) {
		
		boolean ins [] = new boolean [s.length()];
	
		for(int i=0;i<s.length();i++) {
			ins[i]= s.charAt(i)=='1'?true:false;
		}
		return ins;
		
	}
	
	static void arOP(ControlSignals control, boolean [] instruction) throws Exception {
	
		
		
		//register file getting inputs
		boolean [] [] registersToRead=Registers(flipIt(Arrays.copyOfRange(instruction, 4, 8)), flipIt(Arrays.copyOfRange(instruction, 8, 12)), null , null, false);	
		
		//register giving outputs
		boolean [] readData1=registersToRead[0];
		boolean [] readData2=registersToRead[1];
		
        //alu control
		boolean funct [] = flipIt(Arrays.copyOfRange(instruction, 0, 4));
		boolean [] aluControl=ALU_Control(funct, control.ALUOp);
        
        // use alu and get alu result 
        boolean [] aluResult=ALU(readData1, readData2, flipIt(aluControl));
         
        //write result in register file
   		boolean [] writeRegister=Mux(Arrays.copyOfRange(instruction,8, 12), Arrays.copyOfRange(instruction, 12, 16), control.RegDest);
   		Registers(flipIt(Arrays.copyOfRange(instruction, 4, 8)), flipIt(Arrays.copyOfRange(instruction, 8, 12)), writeRegister , aluResult, control.RegWrite); 
   
         
	}
	
	static boolean[][] Registers(boolean[] readRegister1, boolean[] readRegister2, boolean[] writeRegister,
			
			boolean[] writeData, boolean regWrite) {
					/*
					 * returns an Array (length 2) of bits(booleans) position 0 is ReadData1
					 * position 1 is ReadData2
					 */
					boolean[] readData1 = registers[toInt(readRegister1)];
					boolean[] readData2 = registers[toInt(readRegister2)];
					
					
					if(regWrite) {						
						registers[toInt(writeRegister)]=flipIt(writeData);
					}
					
					return new boolean[][] { readData1, readData2 };
				}
	
	static boolean [] flipIt(boolean [] input) {
		boolean output [] = new boolean[input.length];
		for(int i=input.length-1;i>-1;i--) {
			output[output.length-i-1]=input[i];
		}
		return output;
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
		boolean control []= null;
		//System.out.println(functToString(funct));
		if(ALUOp[0]&&ALUOp[1]) {
			//arithamtic
			if(functToString(funct).equals("0000")) {
				//add
				control = toBooleanArray(0);
			}else if(functToString(funct).equals("0001")) {
				//addi
				control = toBooleanArray(0);
			}else if(functToString(funct).equals("0010")) {
				//sub
				control = toBooleanArray(1);
			}else if(functToString(funct).equals("0011")) {
				//mul
				control = toBooleanArray(2);
			}else if(functToString(funct).equals("0100")) {
				//div
				control = toBooleanArray(3);
			}else if(functToString(funct).equals("0101")) {
				//mod
				control = toBooleanArray(4);
			}else if(functToString(funct).equals("0110")) {
				//count
				control = toBooleanArray(5);
			}else if(functToString(funct).equals("0111")) {
				//switch
				control = toBooleanArray(6);
			}else if(functToString(funct).equals("1000")) {
				//power
				control = toBooleanArray(7);
			}else if(functToString(funct).equals("1001")) {
				//absolute
				control = toBooleanArray(8);
			}
		}
		
		return control;
	}

	static String functToString (boolean[] funct) {
		
		String s = "";
		for(int i=0;i<funct.length;i++) {
			if(funct[i])
				s+="1";
			else
				s+="0";
		}
		
		return s;
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
