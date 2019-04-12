package project;

public class ControlSignals {
	boolean RegDest;
	boolean Jump;
	boolean Branch;
	boolean MemRead;
	boolean MemToReg;
	boolean[] ALUOp;
	boolean MemWrite;
	boolean ALUSrc;
	boolean RegWrite;

	ControlSignals() {
		RegDest = false;
		Jump = false;
		Branch = false;
		MemRead = false;
		MemToReg = false;
		ALUOp=new boolean [2];
		ALUOp[0] = false;
		ALUOp[1] = false;
		MemWrite = false;
		ALUSrc = false;
		RegWrite = false;
	}

	ControlSignals(boolean RegDest, boolean Jump, boolean Branch, boolean MemRead, boolean MemToReg, boolean ALUOp,
			boolean MemWrite, boolean ALUSrc, boolean RegWrite) {
		this.RegDest = RegDest;
		this.Jump = Jump;
		this.Branch = Branch;
		this.MemRead = MemRead;
		this.MemToReg = MemToReg;
		this.ALUOp=new boolean [2];
		this.ALUOp[0] = ALUOp[0];
		this.ALUOp[1] = ALUOp[1];
		this.MemWrite = MemWrite;
		this.ALUSrc = ALUSrc;
		this.RegWrite = RegWrite;
	}
}
