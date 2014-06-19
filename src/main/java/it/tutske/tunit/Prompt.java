package it.tutske.tunit;

import java.io.*;


public class Prompt {

	private final ByteArrayOutputStream stream = new ByteArrayOutputStream ();
	private final PrintStream out = new PrintStream (stream);

	public Prompt () {
	}

	public InputStream getInputStream () {
		return new ByteArrayInputStream (new byte [] {});
	}

	public PrintStream getPrintStream () {
		return out;
	}

	public String getPrinted () {
		return new String (stream.toByteArray ());
	}

	public String toString () {
		return String.format ("<Prompt: %s>", getPrinted ());
	}

}
