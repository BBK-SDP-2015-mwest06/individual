# individual


SML Interpreter
===============

Notes:
As the getInstruction method of the Translator class requires the field "line" to already be set, for testing I had to add the field in using reflection as no setter was provided.
This means that I couldn't successfully separate the test from the classes implementation.
Also (initially) the method calls the constructor to create the instruction, which cannot be mocked by Mockito. Hence, the test relies on other classes functionality, which is not ideal.
