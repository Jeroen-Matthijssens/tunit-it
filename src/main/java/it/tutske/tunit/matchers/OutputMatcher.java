package it.tutske.tunit.matchers;

import it.tutske.tunit.Prompt;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class OutputMatcher extends BaseMatcher<Prompt> {

	private Matcher<String> matcher;

	public OutputMatcher (Matcher<String> matcher) {
		this.matcher = matcher;
	}

	public boolean matches (Object item) {
		if ( ! (item instanceof Prompt) ) { return false; }
		Prompt prompt = (Prompt) item;
		return matcher.matches (prompt.getPrinted ());
	}

	public void describeTo (Description description) {
		String msg = "a Prompt that printed output matching ";
		description.appendText (msg);
		description.appendDescriptionOf (matcher);
	}

}
