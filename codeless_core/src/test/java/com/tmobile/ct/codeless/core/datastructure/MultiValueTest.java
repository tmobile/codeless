package com.tmobile.ct.codeless.core.datastructure;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;


public class MultiValueTest {
	@Test
	public void itShouldCreate() {
		MultiValue<String,String> mv = new MultiValue<>();
		Assert.assertNotNull(mv);
	}

	@Test
	public void itShouldCreateAsString() {
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		Assert.assertNotNull(mv);
		Assert.assertEquals("This is a test", mv.getValues().toArray()[0]);
	}

	@Test
	public void itShouldCreateAsList() {
		ArrayList<String> values = new ArrayList<>();
		values.add("This is a test");
		MultiValue<String,String> mv = new MultiValue<>("Test", values);
		Assert.assertNotNull(mv);
		Assert.assertEquals("This is a test", mv.getValues().toArray()[0]);
	}
	
	@Test
	public void itShouldGetAndSetKey() {
		MultiValue<String,String> mv = new MultiValue<>();
		mv.setKey("Test");
		Assert.assertEquals("Test", mv.getKey());
	}
	
	@Test
	public void itShouldGetAndSetValues() {
		MultiValue<String,String> mv = new MultiValue<>();
		ArrayList<String> values = new ArrayList<>();
		values.add("This is a test");
		mv.setValues(values);
		Assert.assertEquals("This is a test", mv.getValues().toArray()[0]);
	}

	@Test
	public void itShouldAddValues() {
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		mv.addValue("This is another test");
		Assert.assertEquals("This is another test", mv.getValues().toArray()[1]);
	}

	@Test
	public void itShouldWriteToString() {
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		Assert.assertEquals("MultiValue [key=Test, values=[This is a test]]", mv.toString());
	}
}
