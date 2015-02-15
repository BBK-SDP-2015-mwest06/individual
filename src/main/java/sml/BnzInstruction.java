package sml;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * If the contents of the testRegister provided is not zero then make nextLabel the next instruction
 * 
 * @author montywest
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class BnzInstruction extends Instruction {

	private int testRegister;
	private String nextLabel;
	
	public BnzInstruction(String label, String opCode) {
		super(label, opCode);
	}

	public BnzInstruction(String label, int testRegister, String nextLabel) {
		this(label, "bnz");
		this.testRegister = testRegister;
		this.nextLabel = nextLabel;
	}
	
	
	@Override
	public void execute(Machine m) {
		int value = m.getRegisters().getRegister(testRegister);
		if (value != 0) {
			Integer nextIndex = null;
			ArrayList<Instruction> prog = m.getProg();
			for (int i = 0; i < prog.size() && nextIndex == null; i++) {
				Instruction instr = prog.get(i);
				if (instr.getLabel().equals(nextLabel))
					nextIndex = i;
			}
			if (nextIndex == null)
				throw new IllegalArgumentException("Bnz insruction label does not exist.");
			else
				m.setPc(nextIndex.intValue());
		}
	}

	@Override
	public String toString() {
		return super.toString() + " if " + testRegister + " is not zero, next instruction is " + nextLabel;
	}
}
