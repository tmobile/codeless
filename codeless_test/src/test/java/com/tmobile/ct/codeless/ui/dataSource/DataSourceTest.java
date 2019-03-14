package com.tmobile.ct.codeless.ui.dataSource;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.junit.Test;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.core.datastructure.SuiteHeaders;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.JsonPathAccessor;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.restassured.RestAssuredHttpClient;
import com.tmobile.ct.codeless.service.test.build.ServiceStepBuilder;
import com.tmobile.ct.codeless.service.test.build.ServiceCallInput;
import com.tmobile.ct.codeless.test.suite.TestImpl;
import com.tmobile.ct.codeless.testdata.RuntimeTestDataSource;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;

public class DataSourceTest extends BaseWiremockTest{

	private com.tmobile.ct.codeless.core.Test test2;
	TestImpl test = new TestImpl();


	@Test
	public void testCreatedDataSource() {
		mockTestData();
		TestData testData = test.getTestData();
		TestDataSource source = test.getTestData().get("empId");
		assertNotNull(source);
		assertTrue(source instanceof RuntimeTestDataSource);

		Accessor accessor = source.getAccessor();
		assertTrue(accessor instanceof JsonPathAccessor);
		assertEquals("id", accessor.value());
	}

	public void mockTestData() {

		ServiceCallInput input = new ServiceCallInput();

		TestData testData = new BasicTestData();
		String key = "host";
		String key_value = "https://google.com";
		StaticTestDataSource staticSource = new StaticTestDataSource(key,key_value);
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setSource("SHEET_DATA");
		value.setSourceClass(null);
		value.setValue(staticSource);

		SourcedDataItem<String,TestDataSource> item = new SourcedDataItem<>(key, value);
		testData.put(key, item);

		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.GET);
		Call call = new Call(new RestAssuredHttpClient(), req, 0);
		String step_name = "serviceStep";
		call.setName(step_name);
		test.addStep(call);

		String export_step = "export::empId::JSONPATH::id";
		input.add(SuiteHeaders.TESTNAME.name(),  new MultiValue<String,String>(SuiteHeaders.TESTNAME.name(), export_step));

		String input_value = new ServiceStepBuilder().parseExport(export_step, test, input);

		input.add(SuiteHeaders.TESTNAME.name(),  new MultiValue<String,String>(SuiteHeaders.TESTNAME.name(), input_value));


	}

}
