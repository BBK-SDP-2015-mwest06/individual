package sml;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BnzInstructionTest {

	@Mock
	private Machine machine;
	
	@Mock
	private Registers registers;
	
	@Mock
	private Instruction instruction0;
	
	@Mock
	private Instruction instruction1;
	
	private ArrayList<Instruction> prog;
	
	private ArgumentCaptor<Integer> intCapture = ArgumentCaptor.forClass(Integer.class);
	
	private BnzInstruction baseInstr;
	
	@Before
	public void setUp() {
		when(machine.getRegisters()).thenReturn(registers);
		baseInstr = new BnzInstruction("f3", "bnz");
		prog = new ArrayList<>();
		prog.add(instruction0);
		prog.add(instruction1);
	}
	
	@Test
	public void testExecuteTrue() {
		int testRegister = 8;
		int testRegisterValue = 3;
		String nextLabel = "f1";
		int nextLabelIndex = prog.indexOf(instruction1);
		
		baseInstr.setTestRegister(testRegister);
		baseInstr.setNextLabel(nextLabel);
		
		when(registers.getRegister(testRegister)).thenReturn(testRegisterValue);
		when(machine.getProg()).thenReturn(prog);
		when(instruction0.getLabel()).thenReturn("f0");
		when(instruction1.getLabel()).thenReturn("f1");
		
		baseInstr.execute(machine);
		
		verify(machine, atLeastOnce()).getRegisters();
		verify(machine, times(1)).setPc(intCapture.capture());
		
		assertEquals(intCapture.getValue().intValue(), nextLabelIndex);
	}
	
	@Test
	public void testExecuteFalse() {
		int testRegister = 8;
		int testRegisterValue = 0;
		String nextLabel = "f1";
		
		baseInstr.setTestRegister(testRegister);
		baseInstr.setNextLabel(nextLabel);
		
		when(registers.getRegister(testRegister)).thenReturn(testRegisterValue);
		when(machine.getProg()).thenReturn(prog);
		when(instruction0.getLabel()).thenReturn("f0");
		when(instruction1.getLabel()).thenReturn("f1");
		
		baseInstr.execute(machine);
		
		verify(machine, atLeastOnce()).getRegisters();
		verify(machine, never()).setPc(intCapture.capture());		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExecuteTrueBadInstruction() {
		int testRegister = 8;
		int testRegisterValue = 3;
		String nextLabel = "f2";
		
		baseInstr.setTestRegister(testRegister);
		baseInstr.setNextLabel(nextLabel);
		
		when(registers.getRegister(testRegister)).thenReturn(testRegisterValue);
		when(machine.getProg()).thenReturn(prog);
		when(instruction0.getLabel()).thenReturn("f0");
		when(instruction1.getLabel()).thenReturn("f1");
		
		baseInstr.execute(machine);
	}
}
