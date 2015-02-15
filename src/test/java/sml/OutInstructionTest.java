package sml;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OutInstructionTest {

	@Mock
	private Machine machine;
	
	@Mock
	private Registers registers;
	
	@Mock
	private PrintStream printStream;
	
	private ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
	
	private OutInstruction baseInstr;
	
	private PrintStream oldPrintStream;
	
	@Before
	public void setUp() {
		when(machine.getRegisters()).thenReturn(registers);
		baseInstr = new OutInstruction("f1", "out");
		oldPrintStream = System.out;
		System.setOut(printStream);
	}
	
	@Test
	public void testExecuteTrue() {
		int testRegister = 9;
		int testRegisterValue = 5;
		
		baseInstr.setRegister(testRegister);
		
		when(registers.getRegister(testRegister)).thenReturn(testRegisterValue);
		
		baseInstr.execute(machine);
		
		verify(machine, atLeastOnce()).getRegisters();
		verify(printStream).println(intCaptor.capture().intValue());
		
		assertEquals(testRegisterValue, intCaptor.getValue().intValue());
	}
	
	@After
	public void teardown() {
		System.setOut(oldPrintStream);
	}
}
