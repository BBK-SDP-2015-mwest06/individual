package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";

	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

		try (Scanner sc = new Scanner(new File(fileName))) {
			// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();
			program = prog;
			program.clear();

			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}

			// Each iteration processes line and reads the next line into line
			while (line != null) {
				// Store the label in label
				String label = scan();

				if (label.length() > 0) {
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}

				try {
					line = sc.nextLine();
				} catch (NoSuchElementException ioE) {
					return false;
				}
			}
		} catch (IOException ioE) {
			System.out.println("File: IO error " + ioE.getMessage());
			return false;
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	public Instruction getInstruction(String label) {
		if (line.equals(""))
			return null;

		String opCode = scan();
		try {
			Class<?> instrClass = this.getInstructionClass(opCode);
			if (!this.isSubclassOfInstruction(instrClass))
				throw new ClassNotFoundException("Class found, but not an Instruction class");
			Constructor<?> constructor = this.getConstructorForInstructionClass(instrClass);
			
			return this.createInstruction(label, constructor);
			
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Instruction: " + opCode + " not found. " + e.getMessage(), e);
		}		
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	private String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' '
				&& line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}

	private Class<?> getInstructionClass(String opCode)
			throws ClassNotFoundException {
		String packageName = "sml";
		String suffix = "Instruction";
		String first = opCode.substring(0, 1);
		String rest = opCode.substring(1, 3);
		String prefix = first.toUpperCase() + rest;
		String className = packageName + "." + prefix + suffix;
		Class<?> instrClass = Class.forName(className);
		return instrClass;
	}

	private boolean isSubclassOfInstruction(Class<?> instrClass) {
		Class<?> superclass = instrClass.getSuperclass();
		while (superclass != null) {
			if (Instruction.class.equals(superclass))
				return true;
			superclass = superclass.getSuperclass();
		}
		return false;
	}

	private Constructor<?> getConstructorForInstructionClass(Class<?> instrClass) {
		Constructor<?>[] allConstructors = instrClass.getDeclaredConstructors();
		if (allConstructors.length == 1) {
			return allConstructors[0];
		} else if (allConstructors.length == 2) {
			Constructor<?> opCodeConstructor;
			try {
				opCodeConstructor = instrClass.getDeclaredConstructor(
						String.class, String.class);
			} catch (NoSuchMethodException | SecurityException e) {
				throw new InternalError(
						"Instruction class has malformed constructors", e);
			}
			return allConstructors[0].equals(opCodeConstructor) ? allConstructors[1]
					: allConstructors[0];
		} else {
			throw new InternalError(
					"Instruction class has too many constructors, should only have two.");
		}
	}
	
	private Instruction createInstruction(String label, Constructor<?> constructor) {
		Class<?>[] paramTypes = constructor.getParameterTypes();
		ArrayList<Object> paramList = new ArrayList<>();
		paramList.add(label);
		for (int i = 1; i < paramTypes.length; i++) {
			Class<?> paramType = paramTypes[i];
			if (String.class.equals(paramType))
				paramList.add(scan());
			else if (int.class.equals(paramType) || Integer.class.equals(paramType))
				paramList.add(scanInt());
			else
				throw new InternalError("Instruction class " + constructor.getName() + " has a malformed constructor, should only accept Strings and ints/Integers.");
		}
		 
		Object[] paramArr = paramList.toArray();
		try {
			Instruction instr = (Instruction) constructor.newInstance(paramArr);
			return instr;
		} catch (InstantiationException e) {
			throw new InternalError("Instruction class " + constructor.getName() + " is abstract.");
		} catch (IllegalAccessException e) {
			throw new InternalError("Instruction class " + constructor.getName() + " has a malformed constructor, should be public");
		} catch (IllegalArgumentException e) {
			throw new InternalError("Instruction class " + constructor.getName() + " has a malformed constructor, first parameter should be label");
		} catch (InvocationTargetException e) {
			throw new InternalError("Instruction class " + constructor.getName() + "'s constructor threw an error", e);
		}
	}
}