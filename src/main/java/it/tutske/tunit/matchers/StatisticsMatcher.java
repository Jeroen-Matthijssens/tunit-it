package it.tutske.tunit.matchers;

import java.util.regex.Pattern;

import org.hamcrest.*;


public class StatisticsMatcher extends BaseMatcher<String> {

	private static final String ptn = "^(?:Testing: )?[.?EF-]*(?: done!)?$"
		+ System.lineSeparator ()
		+ "^([A-Za-z :0-9,]+)$";
	private static final Pattern pattern = Pattern.compile (ptn, Pattern.MULTILINE);
	private Matcher<String> matcher;

	public StatisticsMatcher (Matcher<String> matcher) {
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
		description.appendText ("a statistics line matching ");
		description.appendDescriptionOf (matcher);
	}

	private String getLineFromOutput (String output) {
		java.util.regex.Matcher matcher = pattern.matcher (output);
		matcher.find ();
		return matcher.group (1);
	}

}
