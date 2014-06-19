package it.tutske.tunit;

import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static org.hamcrest.Matchers.*;
import static it.tutske.tunit.matchers.Matchers.*;

import org.junit.*;


public class MarkerTest {

	private final Prompt prompt = new Prompt ();
	private final Application application = new Application (prompt);

	public static class PassingTest {
		@Test public void it_should_pass_a_test () {}
	}

	public static class IgnoredTest {
		@Ignore @Test public void it_should_be_ignored () {}
	}

	public static class AssumptionFailedTest {
		@Test public void it_should_be_ignored () { assumeThat (true, is (false)); }
	}

	public static class FailingTest {
		@Test public void it_should_not_fail () { assertThat (true, is (false)); }
	}

	public static class CrashingTest {
		@Test public void it_should_crash () { throw new RuntimeException ("crash"); }
	}

	@Test
	public void it_should_print_a_dot_for_a_passing_test () throws Exception {
		application.runTest (PassingTest.class);
		assertThat (prompt, printed (markerLine (withMarkers ("."))));
	}

	@Test
	public void it_should_print_a_question_mark_for_skipped_tests () {
		application.runTest (IgnoredTest.class);
		assertThat (prompt, printed (markerLine (withMarkers ("?"))));
	}

	@Test
	public void it_should_print_a_dash_whet_assumtions_where_not_valid () {
		application.runTest (AssumptionFailedTest.class);
		assertThat (prompt, printed (markerLine (withMarkers ("-"))));
	}

	@Test
	public void it_should_print_an_f_when_a_test_fails () {
		application.runTest (FailingTest.class);
		assertThat (prompt, printed (markerLine (withMarkers ("F"))));
	}

	@Test
	public void it_should_print_an_e_when_a_test_crashes () {
		application.runTest (CrashingTest.class);
		assertThat (prompt, printed (markerLine (withMarkers ("E"))));
	}

	@Test
	public void it_should_print_only_one_marker_for_a_test () {
		application.runTest (PassingTest.class);
		assertThat (prompt, printed (markerLine (withMarkersLength (1))));
	}

	@Test
	public void it_should_print_only_one_marker_for_an_ignored_test () {
		application.runTest (IgnoredTest.class);
		assertThat (prompt, printed (markerLine (withMarkersLength (1))));
	}

	@Test
	public void it_should_print_only_one_marker_for_a_assumption_failed_test () {
		application.runTest (AssumptionFailedTest.class);
		assertThat (prompt, printed (markerLine (withMarkersLength (1))));
	}

	@Test
	public void it_should_print_only_one_marker_for_a_failing_test () {
		application.runTest (FailingTest.class);
		assertThat (prompt, printed (markerLine (withMarkersLength (1))));
	}

	@Test
	public void it_should_print_only_one_marker_for_a_crashing_test () {
		application.runTest (CrashingTest.class);
		assertThat (prompt, printed (markerLine (withMarkersLength (1))));
	}

}
