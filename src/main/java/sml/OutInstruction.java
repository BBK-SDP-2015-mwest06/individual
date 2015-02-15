package sml;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class OutInstruction extends Instruction {
	
	private int register;
	
	public OutInstruction(String label, String opCode) {
		super(label, opCode);
	}

	public OutInstruction(String label, int register) {
		this(label, "out");
		this.register = register;
	}
	
	@Override
	public void execute(Machine m) {
		int value = m.getRegisters().getRegister(register);
		System.out.println(value);
	}

	@Override
	public String toString() {
		return super.toString() + " print " + register;
	}
}
