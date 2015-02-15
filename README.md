# individual


SML Interpreter
===============

Running:
	1. Add your sml file to the src folder.
	2. Running:
	Go to the root directory of the project (individual). Run 'java -jar SML.jar <your-sml-filename>'.
	-OR
	Open project in eclipse, go to Run > Run Configurations. Select Java application. Use sml.Machine as the 'Main class' and add your sml filename to the 'Parameter Arguments' box on the 'Arguments tab'


Notes:
As the getInstruction method of the Translator class requires the field "line" to already be set, for testing I had to add the field in using reflection as no setter was provided.
This means that I couldn't successfully separate the test from the classes implementation.
Also (initially) the method calls the constructor to create the instruction, which cannot be mocked by Mockito. Hence, the test relies on other classes functionality, which is not ideal.
Added the singleton pattern to the Machine class to allow for better testing.