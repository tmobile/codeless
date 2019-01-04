package com.tmobile.ct.codeless.core.datastructure;

import org.junit.Test;
import org.junit.Assert;

public class MultiValueMapTest {
	@Test
	public void itShouldCreate() {
		MultiValueMap<String, MultiValue> mvm = new MultiValueMap<>();
		Assert.assertNotNull(mvm);
	}

	@Test
	public void itShouldAdd() {
		MultiValueMap<String, MultiValue> mvm = new MultiValueMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		mvm.add("Test", mv);
		Assert.assertEquals("This is a test", mvm.get("Test").getValues().toArray()[0]);
	}

	@Test
	public void itShouldAddTwoKeys() {
		MultiValueMap<String, MultiValue> mvm = new MultiValueMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		MultiValue<String,String> mv2 = new MultiValue<>("Test", "This is a test");
		mvm.add("Test", mv);
		mvm.add("Test", mv2);
		Assert.assertEquals(2, mvm.get("Test").getValues().toArray().length);
	}

	@Test
	public void itShouldPut() {
		MultiValueMap<String, MultiValue> mvm = new MultiValueMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		mvm.put("Test", mv);
		Assert.assertEquals("This is a test", mvm.get("Test").getValues().toArray()[0]);
	}
}
