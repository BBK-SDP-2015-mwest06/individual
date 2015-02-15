package sml;

import lombok.EqualsAndHashCode;

/***
 * Multiplies the contents of two registers and places the result in a third.
 * 
 * @author montywest
 *
 */
@EqualsAndHashCode(callSuper=true)
public class MulInstruction extends ArithmeticInstruction {
	
	public MulInstruction(String label, String opCode) {
		super(label, opCode);
	}
	
	public MulInstruction(String label, int resultRegister, int op1Register, int op2Register) {
		super(label, "mul", resultRegister, op1Register, op2Register);
	}
	
	@Override
	protected int getOperationResult(int value1, int value2) {
		return value1 * value2;
	}

	@Override
	public String toString() {
		return super.toString() + " " + this.getOp1Register() + " * " + this.getOp2Register() + " to " + this.getResultRegister();
	}
}
