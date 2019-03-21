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
package com.tmobile.ct.codeless.test.excel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.test.BasicExecutor;


public class ExcelSuiteBuilderTest {

	@Test
	public void itShouldBuildSuite(){
		Suite suite = new ExcelSuiteBuilder().build("/suites/test_google.xlsx");
		System.out.println(suite);
		assertThat(suite.getTests()).describedAs("tests").isNotEmpty();
		System.out.println(suite.getConfig());
		suite.getTests().forEach(test ->{
			System.out.println("\n"+test.getName());
			test.getSteps().forEach(step ->{
			System.out.println(step.getName());
			});
		});
	}
	
	@Test
	public void itShouldRunSuite(){
		Suite suites = new ExcelSuiteBuilder().build("/suites/test_google.xlsx");
		BasicExecutor exec = new BasicExecutor();
			suites.getTests().forEach(test -> 
				test.getSteps().forEach( step ->
					exec.run(step)));
	}
}
