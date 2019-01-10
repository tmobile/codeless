package com.tmobile.ct.codeless.service.test.excel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.core.datastructure.SuiteHeaders;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.model.cache.ServiceCache;


public class ExcelServiceCallBuilderTest {

	com.tmobile.ct.codeless.core.Test test;
	Suite suite;

	ServiceCallInput input;

	@Before
	public void setup(){
		test = mock(com.tmobile.ct.codeless.core.Test.class);
		suite = mock(Suite.class);

		when(test.getSuite()).thenReturn(suite);

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
		Call call = new ExcelServiceCallBuilder().build(test, input);

		assertThat(call).describedAs("service call").isNotNull();
	}

	@Test
	public void itShouldValidateTestData(){
		Call call = new ExcelServiceCallBuilder().build(test, input);
		String queryParam = call.getHttpRequest().getQueryParams().get("amount1").getValues().get(0);
		assertThat(queryParam).describedAs("query params").isEqualTo("60");
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
