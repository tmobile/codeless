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
package com.tmobile.ct.codeless.test.csv;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.core.Suite;

public class CsvSuiteBuilderTest {
	
	@Before
	public void setUp() {
        
		CsvConfigTest.setCsvProperties(); 
	}
	
	@Test
	public void itShouldBuildTestSuite() {

		CsvSuiteBuilder builder = new CsvSuiteBuilder();
		Suite suite = builder.build("/suites/ardcsv");
		assertNotNull(suite);
		assertNotNull(suite.getConfig());
		assertNotNull(suite.getTests());
		suite.getTests().forEach(test -> {
			assertNotNull(test.getName());
			test.getSteps().forEach(step -> {
				assertNotNull(step.getName());
			});
		});
	}
	
	@Test
	public void itShouldBuildTestSuiteWithComponent() {

		CsvSuiteBuilder builder = new CsvSuiteBuilder();
		Suite suite = builder.build("/suites/testComponent");
		assertNotNull(suite);
		assertNotNull(suite.getConfig());
		assertNotNull(suite.getTests());
		suite.getTests().forEach(test -> {
			assertNotNull(test.getName());
			test.getSteps().forEach(step -> {
				assertNotNull(step.getName());
			});
		});
	}

}