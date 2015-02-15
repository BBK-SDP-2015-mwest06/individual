package sml;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class MulInstruction extends Instruction {

	private int resultRegister;
	private int op1Register;
	private int op2Register;
	
	public MulInstruction(String label, String opCode) {
		super(label, opCode);
	}
	
	public MulInstruction(String label, int resultRegister, int op1Register, int op2Register) {
		this(label, "mul");
		this.resultRegister = resultRegister;
		this.op1Register = op1Register;
		this.op2Register = op2Register;
	}
	
	@Override
	public void execute(Machine m) {
		Registers r = m.getRegisters();
		int value1 = r.getRegister(op1Register);
		int value2 = r.getRegister(op2Register);
		r.setRegister(resultRegister, getOperationResult(value1, value2));
	}
	
	private int getOperationResult(int value1, int value2) {
		return value1 * value2;
	}

}
