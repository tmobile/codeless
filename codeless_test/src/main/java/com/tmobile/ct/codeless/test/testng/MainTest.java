package com.tmobile.ct.codeless.test.testng;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.test.ExecutionContainer;
import com.tmobile.ct.codeless.test.csv.CsvSuiteBuilder;
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
		Suite suite = null;
		String suitePath = Optional.ofNullable(System.getProperty("SUITE.FILE")).orElse("suites/sampletest.xlsx");
		
		if (System.getProperty("SUITE.FILE") != null) {
			suite = new ExcelSuiteBuilder().build(suitePath);
		} else {
			suitePath = Optional.ofNullable(System.getProperty("SUITE.DIR")).orElse("suites/testard");
			suite = new CsvSuiteBuilder().build(suitePath);
		}

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
				String fileName = parts[1].trim();
				if (fileName.contains(".")) { // .xlsx
					System.setProperty("SUITE.FILE", CodelessConfiguration.getSuiteDir() + "/" + parts[1].trim());
				} else {
					System.setProperty("SUITE.DIR", CodelessConfiguration.getSuiteDir() + "/" + parts[1].trim());
				}

			}
		});
	}
}
