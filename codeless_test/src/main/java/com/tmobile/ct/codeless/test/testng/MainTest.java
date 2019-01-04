package com.tmobile.ct.codeless.test.testng;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.test.excel.ExcelSuiteBuilder;
import com.tmobile.ct.codeless.test.excel.SuiteContainer;

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

		System.setProperty("EXEC.JAR", "true");
		System.setProperty("EXEC.ABSOLUTE.DIR",
				new File(MainTest.class.getProtectionDomain().getCodeSource().getLocation().getFile()).getParent());

		CodelessConfiguration.load();
		parseArgs(args);

		// get test suite
		String suitePath = Optional.ofNullable(System.getProperty("SUITE.FILE")).orElse("suites/demo_actions.xlsx");

		Suite suite = new ExcelSuiteBuilder().build(suitePath);

		if (suite == null || suite.getTests() == null || suite.getTests().size() == 0) {
			throw new RuntimeException("Invliad Test Suite, No Tests Found");
		}

		SuiteContainer.setSuite(suite);

		// build testng suite
		TestNG testng = new TestNG();

		XmlSuite codeless = new XmlSuite();
		codeless.setName(Optional.ofNullable(System.getProperty("SUITE.FILE")).orElse(suite.getName()));

		XmlTest first_test = new XmlTest();
		first_test.setName("codeless service test");
		first_test.setSuite(codeless);

		List<XmlClass> fistlogin_classes = new ArrayList<XmlClass>();
		fistlogin_classes.add(new XmlClass("com.tmobile.ct.codeless.test.testng.TestngTest"));

		first_test.getClasses().addAll(fistlogin_classes);
		codeless.addTest(first_test);

		// add config props to testng test suite
		Map<String, String> params = new HashMap<>();
//        params.put("webdriver.runlocal", "false");
//        params.put("buildMode", "dev");
//        params.put("testEnv", "QLAB06");
//        params.put("metrics.test.type", "E2E");
		params.putAll(suite.getConfig().asMap());
		codeless.setParameters(params);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(codeless);
		testng.setXmlSuites(suites);
		testng.run();

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
