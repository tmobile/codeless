package com.tmobile.ct.codeless.data;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;

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
		HashMap<String, SourcedDataItem<String,String>> newConfig = new HashMap<>();
		String key = "Test";
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		SourcedDataItem<String,String> di = new SourcedDataItem<>(key, value);
		newConfig.put(key, di);
		config.setConfig(newConfig);
		Assert.assertEquals("This is a Test", config.get("Test"));
	}

	@Test
	public void itShouldPutAndGet() {
		Config config = new BasicConfig();
		String key = "Test";
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		SourcedDataItem<String,String> di = new SourcedDataItem<>(key, value);
		config.put(key, di);
		Assert.assertEquals("This is a Test", config.get("Test"));
	}

	@Test
	public void itShouldGetSourcedValue() {
		Config config = new BasicConfig();
		String key = "Test";
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		SourcedDataItem<String,String> di = new SourcedDataItem<>(key, value);
		config.put(key, di);
		Assert.assertEquals(di, config.getSourcedValue("Test"));
	}
}
