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
package com.tmobile.ct.codeless.testdata;

import static org.testng.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;
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
		StaticTestDataSource staticSource = new StaticTestDataSource(key,key_value);
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setSource("SHEET_DATA");
		value.setSourceClass(null);
		value.setValue(staticSource);

		SourcedDataItem<String,TestDataSource> item = new SourcedDataItem<>(key, value);
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
