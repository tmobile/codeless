package com.tmobile.ct.codeless.core.datastructure;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


public class BasicParamMapTest {
	@Test
	public void itShouldCreate() {
		BasicParamMap<MultiValue<String,String>> bpm = new BasicParamMap<>();
		Assert.assertNotNull(bpm);
	}
	
	@Test
	public void itShouldPut() {
		BasicParamMap<MultiValue<String,String>> bpm = new BasicParamMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		bpm.put("Test", mv);
		Assert.assertEquals("This is a test", bpm.get("test").getValues().toArray()[0]);
	}
	
	@Test
	public void itShouldWriteToValuesMap() {
		BasicParamMap<MultiValue<String,String>> bpm = new BasicParamMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		bpm.put("Test", mv);
		Map<String,List<String>> valuesMap = bpm.toValuesMap();
		Assert.assertNotNull(valuesMap);
	}
}
