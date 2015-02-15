package sml;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SubInstruction extends Instruction {

	private int resultRegister;
	private int op1Register;
	private int op2Register;
	
	public SubInstruction(String label, String opCode) {
		super(label, opCode);
	}

	@Override
	public void execute(Machine m) {
		// TODO Auto-generated method stub

	}

}
