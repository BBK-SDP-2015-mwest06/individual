package sml;

import lombok.EqualsAndHashCode;

/***
 * Divides the contents of two registers using integer division and places the result in a third.
 * 
 * @author montywest
 *
 */
@EqualsAndHashCode(callSuper=true)
public class DivInstruction extends ArithmeticInstruction {

	public DivInstruction(String label, String opCode) {
		super(label, opCode);
	}
	
	public DivInstruction(String label, int resultRegister,
			int op1Register, int op2Register) {
		super(label, "div", resultRegister, op1Register, op2Register);
	}

	@Override
	protected int getOperationResult(int value1, int value2) {
		try {
			return value1 / value2;
		} catch(ArithmeticException ex) {
			throw new IllegalArgumentException("You cannot divide by 0");
		}
	}

	@Override
	public String toString() {
		return super.toString() + " " + this.getOp1Register() + " / " + this.getOp2Register() + " to " + this.getResultRegister();
	}
}
