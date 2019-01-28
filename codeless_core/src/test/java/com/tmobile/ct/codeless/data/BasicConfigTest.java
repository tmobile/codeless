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
