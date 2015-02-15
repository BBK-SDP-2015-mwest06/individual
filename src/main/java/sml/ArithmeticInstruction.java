package sml;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public abstract class ArithmeticInstruction extends Instruction {

	private int resultRegister;
	private int op1Register;
	private int op2Register;
	
	public ArithmeticInstruction(String label, String opCode) {
		super(label, opCode);
	}
	
	public ArithmeticInstruction(String label, String opCode, int resultRegister, int op1Register, int op2Register) {
		super(label, opCode);
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

	protected abstract int getOperationResult(int value1, int value2);
	
	@Override
	public String toString() {
		return super.toString();
	}
}
