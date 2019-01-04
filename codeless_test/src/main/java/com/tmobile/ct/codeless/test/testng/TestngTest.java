package com.tmobile.ct.codeless.test.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.tmobile.ct.codeless.core.Executor;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.test.excel.SuiteContainer;
import com.tmobile.ct.codeless.test.extentreport.ExtentTestManager;
import com.tmobile.ct.codeless.test.extentreport.TestStepReporter;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;

/**
 * The Class TestngTest.
 *
 * @author Rob Graff
 * @author Sai Chandra Korpu
 */
@Listeners(com.tmobile.ct.codeless.test.testng.listeners.TestListener.class)
public class TestngTest {

	/** The suite. */
	Suite suite;

	/** The executor. */
	Executor executor = new Executor();

	/**
	 * Suite setup.
	 *
	 * @param context the context
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@BeforeSuite
	public void suiteSetup(ITestContext context) throws IOException {

		File directory = null;
		if (!new File(System.getProperty("user.dir") + "/FailedTestsScreenshots").exists()) {
			boolean created = new File(System.getProperty("user.dir") + "/FailedTestsScreenshots").mkdir();
			if (created) {
				directory = new File(System.getProperty("user.dir") + "/FailedTestsScreenshots");
			}
		} else {
			directory = new File(System.getProperty("user.dir") + "/FailedTestsScreenshots");
			FileUtils.cleanDirectory(directory);
		}

	}

	/**
	 * Beforetest.
	 *
	 * @param testObject the test object
	 */
	@BeforeMethod
	public void beforetest(Object[] testObject) {

	}

	/**
	 * Codeless data provider.
	 *
	 * @return the object[][]
	 */
	@DataProvider(name = "codeless", parallel = false)
	public Object[][] codelessDataProvider() {

		suite = SuiteContainer.getSuite();

		if (suite == null || suite.getTests() == null || suite.getTests().size() == 0) {
			throw new RuntimeException("Invliad Test Suite, No Tests Found");
		}

		Object[][] data = new Object[suite.getTests().size()][1];
		int count = 0;
		for (Test test : suite.getTests()) {
			if (test != null) {
				data[count][0] = test;
				count++;
			}
		}
		return data;
	}

	/**
	 * Execute test.
	 *
	 * @param test the test
	 * @throws Exception the exception
	 */
	@org.testng.annotations.Test(dataProvider = "codeless")
	public void executeTest(Test test) throws Exception {

		ExtentTestManager.getTest().setDescription(test.getName());
		System.out.println("\n=== Running Test " + test.getName() + " ===");
		for (Step u : test.getSteps()) {
			try {
				executor.run(u);
				TestStepReporter.reporter(u);

			} catch (Exception e) {

				TestStepReporter.reporter(u);

			}
		}

		WebDriverFactory.teardown();
		System.out.println("=== End Test " + test.getName() + " ===\n");
	}

}
