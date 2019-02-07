package com.tmobile.ct.codeless.data;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;

public class BasicTestDataTest {
	@Test
	public void itShouldCreate() {
		TestData data = new BasicTestData();
		Assert.assertNotNull(data);
	}

	@Test
	public void itShouldGetConfig() {
		TestData data = new BasicTestData();
		Assert.assertEquals(data, data.getConfig());
	}

	@Test
	public void itShouldSetConfig() {
		TestData data = new BasicTestData();
		HashMap<String, SourcedDataItem<String,TestDataSource>> config = new HashMap<>();
		String key = "Test";
		String key_value = "This is a Test";
		StaticTestDataSource staticSource = new StaticTestDataSource(key,key_value);
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setValue(staticSource);
		SourcedDataItem<String,TestDataSource> di = new SourcedDataItem<>(key, value);
		config.put(key, di);
		data.setConfig(config);
		Assert.assertEquals(key_value, data.get("Test"));
	}

	@Test
	public void itShouldPutAndGet() {
		TestData data = new BasicTestData();
		String key = "Test";
		String key_value = "This is a Test";
		StaticTestDataSource staticSource = new StaticTestDataSource(key,key_value);
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setValue(staticSource);
		SourcedDataItem<String,TestDataSource> di = new SourcedDataItem<>(key, value);
		data.put(key, di);
		Assert.assertEquals("This is a Test", data.get("Test").fullfill());
	}

	@Test
	public void itShouldGetSourcedValue() {
		TestData data = new BasicTestData();
		String key = "Test";
		String key_value = "This is a Test";
		StaticTestDataSource staticSource = new StaticTestDataSource(key, key_value);
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setValue(staticSource);
		SourcedDataItem<String,TestDataSource> di = new SourcedDataItem<>(key, value);
		data.put(key, di);
		Assert.assertEquals(di, data.getSourcedValue("Test"));
	}
}
