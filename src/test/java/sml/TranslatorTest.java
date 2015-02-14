package sml;

import static junit.framework.Assert.assertEquals;

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
		System.out.println("test");
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

	@After
	public void teardown() {
		translatorLineField.setAccessible(false);
	}
}
