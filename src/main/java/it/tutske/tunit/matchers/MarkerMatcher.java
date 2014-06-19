package it.tutske.tunit.matchers;

import java.util.regex.Pattern;

import org.hamcrest.*;


public class MarkerMatcher extends BaseMatcher<String> {

	private static final String ptn = ": ([.?EF-]*) ";
	private static final Pattern pattern = Pattern.compile (ptn);
	private Matcher<String> matcher;

	public MarkerMatcher (Matcher<String> matcher) {
		this.matcher = matcher;
	}

	@Override
	public boolean matches (Object item) {
		if ( ! (item instanceof String) ) { return false; }
		String markers;
		try { markers = extractMarkersFromMarkerLine ((String) item); }
		catch (IllegalStateException ex) { return false; }
		return matcher.matches (markers);
	}

	@Override
	public void describeTo (Description description) {
		description.appendText ("a line with markers matching ");
		description.appendDescriptionOf (matcher);
	}

	private String extractMarkersFromMarkerLine (String markerLine) {
		java.util.regex.Matcher matcher = pattern.matcher (markerLine);
		matcher.find ();
		return matcher.group (1);
	}

}
