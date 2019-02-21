package com.tmobile.ct.codeless.testdata;

import static org.testng.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.data.SourcedDataItem;

public class TestDataProviderTest {


	@Test
	public void testDataProvider() {
		TestData testData = new BasicTestData();
		String key = "host";
		String key_value = "https://google.com";
		SourcedValue<String> value = new SourcedValue<>();
		value.setSource("SHEET_DATA");
		value.setSourceClass(null);
		value.setValue(key_value);

		SourcedDataItem<String,String> item = new SourcedDataItem<>(key, value);
		testData.put(key, item);
		TestDataInput testInput = mockDataProvider(key, testData);

		TestDataProvider dataProvider = testInput.get("INPUT").getValues().get(0);
		assertEquals(key_value, dataProvider.find());
	}

	private TestDataInput mockDataProvider(String key, TestData testData) {
		String var = "{{" + key + "}}";
		TestDataInput datainput = null;
		String[] dataValue = StringUtils.substringsBetween(var, "{{", "}}");
		if(dataValue != null && dataValue.length > 0 ) {
			datainput= new TestDataInput();
			datainput.add("INPUT", new MultiValue<String,TestDataProvider>("INPUT", new TestDataProvider(testData, dataValue[0])));
		}
		return datainput;
	}

}
