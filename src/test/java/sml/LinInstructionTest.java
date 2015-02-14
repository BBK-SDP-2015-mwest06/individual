package sml;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LinInstructionTest {

	@Mock
	private Machine machine;
	
	@Mock
	private Registers registers;
	
	private ArgumentCaptor<Integer> intCapture1 = ArgumentCaptor.forClass(Integer.class);
	private ArgumentCaptor<Integer> intCapture2 = ArgumentCaptor.forClass(Integer.class);
	
	private LinInstruction baseInstr;
	
	@Before
	public void setUp() {
		when(machine.getRegisters()).thenReturn(registers);
		baseInstr = new LinInstruction("f0", "lin");
	}
	
	@Test
	public void testExecute() {
		int register = 2;
		int value = 7;
		
		baseInstr.setRegister(register);
		baseInstr.setValue(value);
		
		baseInstr.execute(machine);
		
		verify(machine, atLeastOnce()).getRegisters();
		verify(registers).setRegister(intCapture1.capture(), intCapture2.capture());
		
		assertEquals(intCapture1.getValue().intValue(), register);
		assertEquals(intCapture2.getValue().intValue(), value);
	}
}
