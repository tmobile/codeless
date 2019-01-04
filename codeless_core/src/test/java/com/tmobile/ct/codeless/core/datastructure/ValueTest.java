package com.tmobile.ct.codeless.core.datastructure;

import org.junit.Test;
import org.junit.Assert;

public class ValueTest {
	@Test
	public void itShouldCreateAsString() {
		Value<String> value = new Value<>();
		value.setValue("Test");
		Assert.assertEquals("Test", value.getValue());
	}

	@Test
	public void itShouldCreateAsObject() {
		Value<Object> value = new Value<>();
		Object test = new Object();
		value.setValue(test);
		Assert.assertEquals(test, value.getValue());
	}

	@Test
	public void itShouldWriteToString() {
		Value<String> value = new Value<>();
		value.setValue("Test");
		Assert.assertEquals("Value [value=Test]", value.toString());
	}
}
