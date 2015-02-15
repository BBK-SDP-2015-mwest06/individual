package sml;

import static junit.framework.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TranslatorTest {

	private final String filename = "test";
	private Translator translator;
	Field translatorLineField;
	
	@Before
	public void setUp() {
		translator = new Translator(filename);
		try {
			translatorLineField = translator.getClass().getDeclaredField("line");
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
		translatorLineField.setAccessible(true);
	}
	
	@Test
	public void testGetInstructionAdd() {
		String label = "f0";
		String line = "add 5 1 3";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		assertEquals("add", instr.getOpcode());
		assertEquals("f0", instr.getLabel());
	}
	
	@Test
	public void testGetInstructionSub() {
		String label = "f0";
		String line = "sub 5 4 2";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		assertEquals("sub", instr.getOpcode());
		assertEquals("f0", instr.getLabel());
	}
	
	@Test
	public void testGetInstructionMul() {
		String label = "f0";
		String line = "mul 5 6 7";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		assertEquals("mul", instr.getOpcode());
		assertEquals("f0", instr.getLabel());
	}
	
	@Test
	public void testGetInstructionDiv() {
		String label = "f0";
		String line = "div 5 5 3";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		assertEquals("div", instr.getOpcode());
		assertEquals("f0", instr.getLabel());
	}
	
	@Test
	public void testGetInstructionOut() {
		String label = "f0";
		String line = "out 5";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		assertEquals("out", instr.getOpcode());
		assertEquals("f0", instr.getLabel());
	}
	
	@Test
	public void testGetInstructionLin() {
		String label = "f0";
		String line = "lin 5 3";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		assertEquals("lin", instr.getOpcode());
		assertEquals("f0", instr.getLabel());
	}
	
	@Test
	public void testGetInstructionBnz() {
		String label = "f0";
		String line = "bnz 5 f1";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		assertEquals("bnz", instr.getOpcode());
		assertEquals("f0", instr.getLabel());
	}
	
	@Test
	public void testGetAddInstruction() {
		String label = "f0";
		String line = "add 5 1 3";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		
		assertTrue(instr instanceof AddInstruction);
		AddInstruction addInstr = (AddInstruction) instr;
		assertEquals(5, addInstr.getResultRegister());
		assertEquals(1, addInstr.getOp1Register());
		assertEquals(3, addInstr.getOp2Register());
	}
	
	@Test
	public void testGetLinInstruction() {
		String label = "f0";
		String line = "lin 5 3";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		assertTrue(instr instanceof LinInstruction);
		
		LinInstruction linInstr = (LinInstruction) instr;
		assertEquals(5, linInstr.getRegister());
		assertEquals(3, linInstr.getValue());
	}
	
	@Test
	public void testGetSubInstruction() {
		String label = "f0";
		String line = "sub 5 1 3";
		
		try {
			translatorLineField.set(translator, line);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		Instruction instr = translator.getInstruction(label);
		
		assertTrue(instr instanceof SubInstruction);
		SubInstruction addInstr = (SubInstruction) instr;
		assertEquals(5, addInstr.getResultRegister());
		assertEquals(1, addInstr.getOp1Register());
		assertEquals(3, addInstr.getOp2Register());
	}

	@After
	public void teardown() {
		translatorLineField.setAccessible(false);
	}
}
