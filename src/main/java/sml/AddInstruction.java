package sml;

import lombok.EqualsAndHashCode;

/**
 * Adds the contents of two registers and places the result in a third.
 * 
 * @author someone
 */
@EqualsAndHashCode(callSuper=true)
public class AddInstruction extends ArithmeticInstruction {

	public AddInstruction(String label, String op) {
		super(label, op);
	}

	public AddInstruction(String label, int resultRegister, int op1Register, int op2Register) {
		super(label, "add", resultRegister, op1Register, op2Register);
	}
	
	@Override
	protected int getOperationResult(int value1, int value2) {
		return value1 + value2;
	}

	@Override
	public String toString() {
		return super.toString() + " " + this.getOp1Register() + " + " + this.getOp2Register() + " to " + this.getResultRegister();
	}
}
