package com.tmobile.ct.codeless.ui.yaml.test;

import org.junit.Test;

import com.tmobile.ct.codeless.ui.model.ControlElement;

import org.junit.Assert;

public class ControlElementTest {

	private static ControlElement controlElement = new ControlElement();

	@Test
	public void testSetAndGetLocator() {
		String expected = "Locator";
		controlElement.setBy(expected);
		Assert.assertEquals(controlElement.getBy(), expected);
	}

	@Test
	public void testSetAndGetLocatorValue() {
		String expected = "LocatorValue";
		controlElement.setLocator(expected);
		Assert.assertEquals(controlElement.getLocator(), expected);
	}

	public static ControlElement createControlElement() {

		controlElement.setBy("locator");
		controlElement.setLocator("locatorValue");
		
		return controlElement;

	}

}
