package it.tutske.tunit.matchers;

import java.util.regex.Pattern;

import org.hamcrest.*;


public class StatisticMatcher extends BaseMatcher<String> {

	private String name;
	private int statistic;

	public StatisticMatcher (String name, int statistic) {
		this.name = name;
		this.statistic = statistic;
	}

	@Override
	public boolean matches (Object item) {
		if ( ! (item instanceof String) ) { return false; }

		Pattern pattern = Pattern.compile (name + ": (\\d*)(?:(, )|$)");
		java.util.regex.Matcher matcher = pattern.matcher ((String) item);
		matcher.find ();

		String number;
		try { number = matcher.group (1); }
		catch (IllegalStateException ex) { return false; }

		return Integer.toString (statistic).equals (number);
	}

	@Override
	public void describeTo (Description description) {
		String tpl = "scoring %d on statistic %s";
		String msg = String.format (tpl, statistic, name);
		description.appendText (msg);
	}

}
