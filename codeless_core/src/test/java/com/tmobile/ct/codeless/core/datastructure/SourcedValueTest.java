package com.tmobile.ct.codeless.core.datastructure;

import org.junit.Test;
import org.junit.Assert;

public class SourcedValueTest {
	@Test
	public void itShouldCreate() {
		SourcedValue<String> value = new SourcedValue<>();
		Assert.assertNotNull(value);
	}
	
	@Test
	public void itShouldGetAndSetSource() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setSource(SourcedValueTest.class.getName());
		Assert.assertEquals(SourcedValueTest.class.getName(), value.getSource());
	}
	
	@Test
	public void itShouldGetAndSetSourceClass() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setSourceClass(SourcedValueTest.class);
		Assert.assertEquals(SourcedValueTest.class, value.getSourceClass());
	}
	
	@Test
	public void itShouldGetAndSetValue() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("Test");
		Assert.assertEquals("Test", value.getValue());
	}
	
	@Test
	public void itShouldWriteToString() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setSource(SourcedValueTest.class.getName());
		value.setSourceClass(SourcedValueTest.class);
		value.setValue("Test");
		String expected = "SourcedValue [source=" + value.getSource() + ", sourceClass=" + value.getSourceClass() + ", value=" + value.getValue() + "]";
		Assert.assertEquals(expected, value.toString());
	}
}
