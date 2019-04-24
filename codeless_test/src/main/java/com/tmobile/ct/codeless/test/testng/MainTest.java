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
import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.config.Config;
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
			throw new RuntimeException("Invalid Test Suite, No Tests Found");
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
			String fileName = "";
			if (parts.length == 2) {
				fileName = parts[1].trim();
			}
			if (parts[0].equalsIgnoreCase("-SUITE")) {
				if (fileName.contains(Config.EXCEL_EXTENSION)) { // .xlsx
					System.setProperty("SUITE.FILE", CodelessConfiguration.getSuiteDir() + "/" + fileName);
				} else {
					System.setProperty("SUITE.DIR", CodelessConfiguration.getSuiteDir() + "/" + fileName);
				}
			} else if (parts[0].equalsIgnoreCase("-DATASHEET") && StringUtils.isNotBlank(fileName)) {
				System.setProperty(Config.ENV_TESTDATA_SHEETNAME, fileName);
			}
		});
	}
}
