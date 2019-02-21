package com.tmobile.ct.codeless.test.testng;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.test.ExecutionContainer;
import com.tmobile.ct.codeless.test.excel.ExcelSuiteBuilder;

/**
 * The Class MainTest.
 *
 * @author Rob Graff
 */
public class MainTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		configSetup(args);

		// create the execution container
		Execution execution = new TestngExecution();
		ExecutionContainer.setExecution(execution);

		// build test suite
		String suitePath = Optional.ofNullable(System.getProperty("SUITE.FILE")).orElse("suites/sampletest.xlsx");

		Suite suite = new ExcelSuiteBuilder().build(suitePath);

		if (suite == null || suite.getTests() == null || suite.getTests().size() == 0) {
			throw new RuntimeException("Invliad Test Suite, No Tests Found");
		}

		suite.setExecution(execution);

		// run execution
		execution.addSuite(suite);
		execution.run();

	}

	private static void configSetup(String[] args) {
		System.setProperty("EXEC.JAR", "true");
		System.setProperty("EXEC.ABSOLUTE.DIR",
				new File(MainTest.class.getProtectionDomain().getCodeSource().getLocation().getFile()).getParent());

		CodelessConfiguration.load();
		parseArgs(args);
	}

	/**
	 * Parses the args.
	 *
	 * @param args the args
	 */
	private static void parseArgs(String[] args) {
		Arrays.asList(args).forEach(arg -> {
			String[] parts = arg.trim().split("=");
			if (parts[0].equalsIgnoreCase("-SUITE")) {
				System.setProperty("SUITE.FILE", CodelessConfiguration.getSuiteDir() + "/" + parts[1].trim());
			}
		});

	}
}
