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
package com.tmobile.ct.codeless.data;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;

public class BasicConfigTest {
	@Test
	public void itShouldCreate() {
		Config config = new BasicConfig();
		Assert.assertNotNull(config);
	}

	@Test
	public void itShouldGetConfig() {
		Config config = new BasicConfig();
		Assert.assertEquals(config, config.getConfig());
	}

	@Test
	public void itShouldSetConfig() {
		Config config = new BasicConfig();
		HashMap<String, SourcedDataItem<String,TestDataSource>> newConfig = new HashMap<>();
		String key = "Test";
		String key_value = "This is a Test";
		StaticTestDataSource staticSource = new StaticTestDataSource(key,key_value);
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setValue(staticSource);
		SourcedDataItem<String,TestDataSource> di = new SourcedDataItem<>(key, value);
		newConfig.put(key, di);
		config.setConfig(newConfig);
		Assert.assertEquals(key_value, config.get("Test"));
	}

	@Test
	public void itShouldPutAndGet() {
		Config config = new BasicConfig();
		String key = "Test";
		String key_value = "This is a Test";
		StaticTestDataSource staticSource = new StaticTestDataSource(key, key_value);
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setValue(staticSource);
		SourcedDataItem<String,TestDataSource> di = new SourcedDataItem<>(key, value);
		config.put(key, di);
		Assert.assertEquals(key_value, config.get("Test"));
	}

	@Test
	public void itShouldGetSourcedValue() {
		Config config = new BasicConfig();
		String key = "Test";
		String key_value = "This is a Test";
		StaticTestDataSource staticSource = new StaticTestDataSource(key, key_value);
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setValue(staticSource);
		SourcedDataItem<String,TestDataSource> di = new SourcedDataItem<>(key, value);
		config.put(key, di);
		Assert.assertEquals(di, config.getSourcedValue("Test"));
	}
}
