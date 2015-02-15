# individual


SML Interpreter
===============

<h4>Running:</h4>
<ol>
	<li>Add your sml file to the src folder.</li>
	<li>Running: Go to the root directory of the project (individual). Run 'java -jar SML.jar (your-sml-filename)'.</li>
</ol>

<h4>Notes:</h4>
<ul>
<li>As the getInstruction method of the Translator class requires the field "line" to already be set, for testing I had to add the field in using reflection as no setter was provided.</li>
<li>This means that I couldn't successfully separate the test from the classes implementation.</li>
<li>Also (initially) the method calls the constructor to create the instruction, which cannot be mocked by Mockito. Hence, the test relies on other classes functionality, which is not ideal.</li>
<li>Added the singleton pattern to the Machine class to allow for better testing.</li>