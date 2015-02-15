package sml;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MulInstructionTest {
	
	@Mock
	private Machine machine;
	
	@Mock
	private Registers registers;
	
	private ArgumentCaptor<Integer> intCapture1 = ArgumentCaptor.forClass(Integer.class);
	private ArgumentCaptor<Integer> intCapture2 = ArgumentCaptor.forClass(Integer.class);
	
	private MulInstruction baseInstr;
	
	@Before
	public void setUp() {
		when(machine.getRegisters()).thenReturn(registers);
		baseInstr = new MulInstruction("f0", "mul");
	}
	
	@Test
	public void testExecute() {
		int resultRegister = 19;
		int op1Register = 5;
		int op2Register = 6;
		int op1Value = 11;
		int op2Value = 2;
		
		baseInstr.setResultRegister(resultRegister);
		baseInstr.setOp1Register(op1Register);
		baseInstr.setOp2Register(op2Register);
		
		when(registers.getRegister(op1Register)).thenReturn(op1Value);
		when(registers.getRegister(op2Register)).thenReturn(op2Value);
		
		baseInstr.execute(machine);
		
		verify(machine, atLeastOnce()).getRegisters();
		verify(registers).setRegister(intCapture1.capture(), intCapture2.capture());
		
		assertEquals(intCapture1.getValue().intValue(), resultRegister);
		assertEquals(intCapture2.getValue().intValue(), op1Value * op2Value);
	}
}
