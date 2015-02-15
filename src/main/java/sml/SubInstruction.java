package sml;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
public class SubInstruction extends ArithmeticInstruction {
	
	public SubInstruction(String label, String opCode) {
		super(label, opCode);
	}
	
	public SubInstruction(String label, int resultRegister, int op1Register, int op2Register) {
		super(label, "sub", resultRegister, op1Register, op2Register);
	}
	
	@Override
	protected int getOperationResult(int value1, int value2) {
		return value1 - value2;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.getOp1Register() + " - " + this.getOp2Register() + " to " + this.getResultRegister();
	}

}
