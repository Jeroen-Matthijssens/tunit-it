package it.tutske.tunit;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;
import static org.hamcrest.Matchers.*;
import static it.tutske.tunit.matchers.Matchers.*;

import org.junit.*;


public class StatisticTest {

	private final Prompt prompt = new Prompt ();
	private final Application application = new Application (prompt);

	public static class BunchOfTests {
		@Test public void first () { assumeThat (true, is (false)); }
		@Test public void second () {}
		@Ignore @Test public void ignored () {}
		@Test public void error () { throw new RuntimeException ("crash"); }
		@Test public void third () { fail ("don't pass"); }
		@Test public void pass () {}
		@Test public void forth () { fail ("don't pass"); }
		@Test public void assumption_one () { assumeThat (true, is (false)); }
		@Test public void fifth () {}
		@Test public void second_assumption () { assumeThat (true, is (false)); }
		@Ignore @Test public void dont_run () {}
	}

	@Test
	public void it_should_count_the_amount_of_tests_it_runs () {
		application.runTest (BunchOfTests.class);
		assertThat (prompt, printed (statistic ("Tests run", 9)));
	}

	@Test
	public void it_should_count_the_amount_of_tests_that_have_failed_assumptions () {
		application.runTest (BunchOfTests.class);
		assertThat (prompt, printed (statistic ("Assumptions", 3)));
	}

	@Test
	public void it_should_count_the_amount_of_tests_that_fail () {
		application.runTest (BunchOfTests.class);
		assertThat (prompt, printed (statistic ("Failures", 2)));
	}

	@Test
	public void it_should_count_the_amount_of_tests_that_are_ignored () {
		application.runTest (BunchOfTests.class);
		assertThat (prompt, printed (statistic ("Skipped", 2)));
	}

	@Test
	public void it_should_count_the_amount_of_tests_that_are_in_error () {
		application.runTest (BunchOfTests.class);
		assertThat (prompt, printed (statistic ("Errors", 1)));
	}

}
