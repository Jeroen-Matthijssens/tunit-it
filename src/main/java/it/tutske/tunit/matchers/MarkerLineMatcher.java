package it.tutske.tunit.matchers;

import java.util.regex.Pattern;

import org.hamcrest.*;


public class MarkerLineMatcher extends BaseMatcher<String> {

	private static final String ptn = "^(Testing: [.?EF-]* done!)$";
	private static final Pattern pattern = Pattern.compile (ptn, Pattern.MULTILINE);
	private Matcher<String> matcher;

	public MarkerLineMatcher (Matcher<String> matcher) {
		this.matcher = matcher;
	}

	@Override
	public boolean matches (Object item) {
		if ( ! (item instanceof String) ) { return false; }
		String line;
		try { line = getLineFromOutput ((String) item); }
		catch (IllegalStateException ex) { return false; }
		return matcher.matches (line);
	}

	@Override
	public void describeTo (Description description) {
		description.appendText ("a markerline matching ");
		description.appendDescriptionOf (matcher);
	}

	private String getLineFromOutput (String output) {
		java.util.regex.Matcher matcher = pattern.matcher (output);
		matcher.find ();
		return matcher.group (1);
	}

}
