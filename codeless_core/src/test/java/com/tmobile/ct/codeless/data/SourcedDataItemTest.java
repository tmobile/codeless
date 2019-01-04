package com.tmobile.ct.codeless.data;

import org.junit.Test;
import org.junit.Assert;

import com.tmobile.ct.codeless.core.datastructure.SourcedValue;


public class SourcedDataItemTest {
	@Test
	public void itShouldCreateBlank() {
		SourcedDataItem<String,String> di = new SourcedDataItem<>();
		Assert.assertNotNull(di);
	}
	
	@Test
	public void itShouldCreateWithArgs() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		SourcedDataItem<String,String> di = new SourcedDataItem<>("Test", value);
		Assert.assertEquals("Test", di.getKey());
		Assert.assertEquals("This is a Test", di.getValue().getValue());
	}
	
	@Test
	public void itShouldGetAndSetKeys() {
		SourcedDataItem<String,String> di = new SourcedDataItem<>();
		di.setKey("Test");
		Assert.assertEquals("Test", di.getKey());
	}
	
	@Test
	public void itShouldGetAndSetValues() {
		SourcedDataItem<String,String> di = new SourcedDataItem<>();
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		di.setValue(value);
		Assert.assertEquals("This is a Test", di.getValue().getValue());
	}	

	@Test
	public void itShouldWriteToString() {
		SourcedDataItem<String,String> di = new SourcedDataItem<>();
		di.setKey("Test");
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		di.setValue(value);
		Assert.assertEquals("SourcedConfigItem [key=Test, value=SourcedValue [source=null, sourceClass=null, value=This is a Test]]", di.toString());
	}
}
