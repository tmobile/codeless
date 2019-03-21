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
package com.tmobile.ct.codeless.service.test.build;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.core.datastructure.SuiteHeaders;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.model.cache.ServiceCache;
import com.tmobile.ct.codeless.service.test.build.ServiceStepBuilder;
import com.tmobile.ct.codeless.service.test.build.ServiceCallInput;


public class ServiceStepBuilderTest {

	com.tmobile.ct.codeless.core.Test test;
	Suite suite;

	ServiceCallInput input;

	@Before
	public void setup(){
		test = mock(com.tmobile.ct.codeless.core.Test.class);
		suite = mock(Suite.class);

		when(test.getSuite()).thenReturn(suite);
		when(test.getName()).thenReturn("TestName");
        when(test.getTestData()).thenReturn(new BasicTestData());

		input = new ServiceCallInput();
		input.add(SuiteHeaders.TESTNAME.name(), new MultiValue(SuiteHeaders.TESTNAME.name(), "test"));
		input.add(SuiteHeaders.SERVICE.name(), new MultiValue(SuiteHeaders.SERVICE.name(), "bank"));
		input.add(SuiteHeaders.METHOD.name(), new MultiValue(SuiteHeaders.METHOD.name(), "POST"));
		input.add(SuiteHeaders.OPERATION.name(), new MultiValue(SuiteHeaders.OPERATION.name(), "/account/deposit"));
		input.add(SuiteHeaders.TESTDATA.name(), new MultiValue(SuiteHeaders.TESTDATA.name(), "query::amount1::60"));
		input.add(SuiteHeaders.TESTDATA.name(), new MultiValue(SuiteHeaders.TESTDATA.name(), "header::auth::test"));
		String body = String.format("{user:userPM}");
		input.add(SuiteHeaders.TESTDATA.name(), new MultiValue(SuiteHeaders.TESTDATA.name(), "body::string::"+body));

		ServiceCache.setModelDir("/model");
	}

	@Test
	public void itShouldBuildSuite(){
		Call call = new ServiceStepBuilder().build(test, input);

		assertThat(call).describedAs("service call").isNotNull();
	}

	@Test
	public void itShouldValidateTestData(){
		Call call = new ServiceStepBuilder().build(test, input);
		String queryParam = call.getHttpRequest().getQueryParams().get("amount1").getValues().get(0);
		assertThat(queryParam).describedAs("query params").isEqualTo("60");
	}

	@Test
    public void parseExportTest() {

        ServiceStepBuilder callBuilder = new ServiceStepBuilder();
        String stepName = input.get(SuiteHeaders.TESTNAME.name()).getValues().get(0);
        String expectedResult = "$REF~TestName~" + stepName + "~header~authorization";

        callBuilder.parseExport("export::abc::header::authorization", test, input);
        String output = callBuilder.parseExport("{{abc}}", test, input);

        assertNotNull(test.getTestData().getSourcedValue("abc"));
        assertEquals(expectedResult, output);
    }

	// TODO Replace with mock infrastructure so calls can be executed

//	@Test
//	public void itShouldRunSuite(){
//		List<Suite> suites = new ExcelSuiteBuilder().build("/suites/bank2.xlsx");
//		assertThat(suites).describedAs("suites").isNotEmpty();
//		Executor exec = new Executor();
//		suites.forEach( suite ->
//			suite.getTests().forEach(test ->
//				test.getSteps().forEach( step ->
//					exec.run(step))));
//	}
}
