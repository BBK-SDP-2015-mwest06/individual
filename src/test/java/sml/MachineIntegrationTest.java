package sml;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MachineIntegrationTest {

	private Machine machine = Machine.getInstance();
	
	@Test
	public void linIntegrationTest() {
		Machine.main( new String[] {"test/lintest.sml"});
		
		assertEquals(5, machine.getRegisters().getRegister(24));
		assertEquals(3, machine.getRegisters().getRegister(23));
	}
	
	@Test
	public void addIntegrationTest() {
		Machine.main( new String[] {"test/addtest.sml"});
		
		assertEquals(20, machine.getRegisters().getRegister(1));
		assertEquals(13, machine.getRegisters().getRegister(2));
	}
	
	@Test
	public void subIntegrationTest() {
		Machine.main( new String[] {"test/subtest.sml"});
		
		assertEquals(14, machine.getRegisters().getRegister(11));
		assertEquals(8, machine.getRegisters().getRegister(12));
	}
	
	@Test
	public void mulIntegrationTest() {
		Machine.main( new String[] {"test/multest.sml"});
		
		assertEquals(20, machine.getRegisters().getRegister(19));
		assertEquals(80, machine.getRegisters().getRegister(18));
	}
	
	@Test
	public void divIntegrationTest() {
		Machine.main( new String[] {"test/divtest.sml"});
		
		assertEquals(5, machine.getRegisters().getRegister(2));
		assertEquals(2, machine.getRegisters().getRegister(1));
	}
	
	@Test
	public void bnzIntegrationTest() {
		Machine.main( new String[] {"test/bnztest.sml"});
		
		assertEquals(3, machine.getRegisters().getRegister(5));
		assertEquals(10, machine.getRegisters().getRegister(6));
		assertEquals(1, machine.getRegisters().getRegister(7));
		assertEquals(10, machine.getRegisters().getRegister(8));
	}
}
