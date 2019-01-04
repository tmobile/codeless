package com.tmobile.ct.codeless.data;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;

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
		HashMap<String, SourcedDataItem<String,String>> config = new HashMap<>();
		String key = "Test";
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		SourcedDataItem<String,String> di = new SourcedDataItem<>(key, value);
		config.put(key, di);
		data.setConfig(config);
		Assert.assertEquals("This is a Test", data.get("Test"));
	}

	@Test
	public void itShouldPutAndGet() {
		TestData data = new BasicTestData();
		String key = "Test";
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		SourcedDataItem<String,String> di = new SourcedDataItem<>(key, value);
		data.put(key, di);
		Assert.assertEquals("This is a Test", data.get("Test"));
	}

	@Test
	public void itShouldGetSourcedValue() {
		TestData data = new BasicTestData();
		String key = "Test";
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		SourcedDataItem<String,String> di = new SourcedDataItem<>(key, value);
		data.put(key, di);
		Assert.assertEquals(di, data.getSourcedValue("Test"));
	}
}
