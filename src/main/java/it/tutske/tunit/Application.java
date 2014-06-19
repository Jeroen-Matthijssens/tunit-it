package it.tutske.tunit;

import org.tutske.tunit.TestMain;


public class Application {

	private final Prompt prompt;

	public Application (Prompt prompt) {
		this.prompt = prompt;
	}

	public void runTest (Class<?> clazz) {
		new TestMain (prompt.getPrintStream ()).run (clazz);
	}

}
