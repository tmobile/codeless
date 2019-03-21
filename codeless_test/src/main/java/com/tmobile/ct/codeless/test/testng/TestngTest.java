/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.ct.codeless.test.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.test.BasicExecutor;
import com.tmobile.ct.codeless.test.ExecutionContainer;
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
public class TestngTest{
	
	Suite suite;

	BasicExecutor executor = new BasicExecutor();

	Execution execution;

	/**
	 * Suite setup.
	 *
	 * @param context the context
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@BeforeSuite
	public void beforeSuite(ITestContext context) throws IOException {

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
		
		// get suite container from execution
		String id = context.getCurrentXmlTest().getParameter("codeless.suite.id");
		execution = ExecutionContainer.getExecution();
		suite = execution.getSuite(id);
		execution.getSuiteHooks().forEach(hook ->{
			hook.beforeSuite(suite);
		});

	}
	
	@AfterSuite
	public void afterSuite(){
		execution.getSuiteHooks().forEach(hook ->{
			hook.afterSuite(suite);
		});
	}

	/**
	 * Codeless data provider - data driver spawn 1 method for each Test object in the Suite
	 *
	 * @return the object[][]
	 */
	@DataProvider(name = "codeless", parallel = false)
	public Object[][] codelessDataProvider() {
		
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

		// before test
		execution.getTestHooks().forEach(hook -> {
			hook.beforeTest(test);
		});

		try {

			ExtentTestManager.getTest().setDescription(test.getName());

			// execute steps
			for (Step step : test.getSteps()) {

				execution.getStepHooks().forEach(hook -> {
					hook.beforeStep(step);
				});

				try {
					executor.run(step);
				} catch (Exception e) {
					throw e;
				} finally {

					TestStepReporter.reporter(step);
					execution.getStepHooks().forEach(hook -> {
						hook.afterStep(step);
					});
				}
			}
			WebDriverFactory.teardown();

		} catch (Exception e) {
			throw e;
		} finally {
			
			// after test
			execution.getTestHooks().forEach(hook -> {
				hook.afterTest(test);
			});
		}
	}

}
