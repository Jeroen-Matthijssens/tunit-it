package it.tutske.tunit;

import org.tutske.tunit.TestMain;


public class Main extends TestMain {

	static {
		TEST_CLASSES = new Class<?> [] { MarkerTest.class, StatisticTest.class };
	}

}
