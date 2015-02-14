package sml;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class ....
 * 
 * @author someone
 */

@Data
@EqualsAndHashCode(callSuper=true)
public class AddInstruction extends Instruction {

	private int resultRegister;
	private int op1Register;
	private int op2Register;

	public AddInstruction(String label, String op) {
		super(label, op);
	}

	public AddInstruction(String label, int resultRegister, int op1Register, int op2Register) {
		this(label, "add");
		this.resultRegister = resultRegister;
		this.op1Register = op1Register;
		this.op2Register = op2Register;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1Register);
		int value2 = m.getRegisters().getRegister(op2Register);
		m.getRegisters().setRegister(resultRegister, value1 + value2);
	}

	@Override
	public String toString() {
		return super.toString() + " " + op1Register + " + " + op2Register + " to " + resultRegister;
	}
}
