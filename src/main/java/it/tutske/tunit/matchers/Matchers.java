package it.tutske.tunit.matchers;

import static org.hamcrest.Matchers.*;
import it.tutske.tunit.Prompt;

import org.hamcrest.Matcher;


public class Matchers {

	public static Matcher<Prompt> printed (String out) {
		return printed (is (out));
	}

	public static Matcher<Prompt> printed (Matcher<String> matcher) {
		return new OutputMatcher (matcher);
	}

	public static Matcher<String> markerLine (Matcher<String> matcher) {
		return new MarkerLineMatcher (matcher);
	}

	public static Matcher<String> statistic (String name, int number) {
		return new StatisticsMatcher (new StatisticMatcher (name, number));
	}

	public static Matcher<String> withMarkers (String markers) {
		return withMarkers (containsString (markers));
	}

	public static Matcher<String> withMarkers (Matcher<String> matcher) {
		return new MarkerMatcher (matcher);
	}

	public static Matcher<String> withMarkersLength (int length) {
		return new MarkerMatcher (new StringLengthMatcher (length));
	}

	public static Matcher<String> outputContaining (String out) {
		return containsString (out);
	}

}
