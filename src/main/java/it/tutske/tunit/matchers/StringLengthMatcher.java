package it.tutske.tunit.matchers;

import org.hamcrest.*;


public class StringLengthMatcher extends BaseMatcher<String> {

	private int length;

	public StringLengthMatcher (int length) {
		this.length = length;
	}

	@Override
	public boolean matches (Object item) {
		if ( ! (item instanceof String) ) { return false; }
		return ((String) item).length () == length;
	}

	@Override
	public void describeTo (Description description) {
		description.appendText ("a string with length " + length + ".");
	}

}
