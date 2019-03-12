package com.tmobile.ct.codeless.ui.build.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.tmobile.ct.codeless.ui.build.UiTestStep;

import org.junit.Assert;

public class UiTestStepTest {

	private static UiTestStep uiTestRow = new UiTestStep();

	@Test
	public void testSetAndGetAction() {
		String expected = "Action";
		uiTestRow.setAction(expected);
		Assert.assertEquals(uiTestRow.getAction(), expected);
	}

	@Test
	public void testSetAndGetInput() {
		String expected = "Input";
		uiTestRow.setInput(expected);
		Assert.assertEquals(uiTestRow.getInput(), expected);
	}

	@Test
	public void testSetAndGetTarget() {
		String expected = "Target";
		uiTestRow.setTarget(expected);
		Assert.assertEquals(uiTestRow.getTarget(), expected);
	}

	@Test
	public void testSetAndAction_Override() {
		String expected = "Action_Override";
		uiTestRow.setAction_Override(expected);
		Assert.assertEquals(uiTestRow.getAction_Override(), expected);
	}

	@Test
	public void testSetAndGetWaitType() {
		String expected = "WaitType";
		uiTestRow.setWaitType(expected);
		Assert.assertEquals(uiTestRow.getWaitType(), expected);
	}

	@Test
	public void testSetAndGetStep() {
		String expected = "Step";
		uiTestRow.setStep(expected);
		Assert.assertEquals(uiTestRow.getStep(), expected);
	}

	@Test
	public void testSetAndGetWaitTime_Override() {
		String expected = "WaitTime_Override";
		uiTestRow.setWaitTime_Override(expected);
		Assert.assertEquals(uiTestRow.getWaitTime_Override(), expected);
	}

	@Test
	public void testSetAndGetTestData() {
		List<String> expected = Arrays.asList("a", "b", "c");
		uiTestRow.setTestData(expected);
		Assert.assertEquals(uiTestRow.getTestData(), expected);
	}

	public static UiTestStep createExcelUiTestRow() {

		List<String> testData = Arrays.asList("a", "b", "c");

		uiTestRow.setAction("action");
		uiTestRow.setAction_Override("action_Override");
		uiTestRow.setInput("input");
		uiTestRow.setStep("step");
		uiTestRow.setTarget("target");
		uiTestRow.setTestData(testData);
		uiTestRow.setWaitTime_Override("waitTime_Override");
		uiTestRow.setWaitType("waitType");

		return uiTestRow;

	}

}
