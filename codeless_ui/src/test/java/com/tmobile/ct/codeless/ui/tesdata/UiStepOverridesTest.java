package com.tmobile.ct.codeless.ui.tesdata;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;

import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.ui.UiStep;
import com.tmobile.ct.codeless.ui.UiStepImpl;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;
import com.tmobile.ct.codeless.ui.build.UiTestStep;
import com.tmobile.ct.codeless.ui.testdata.UiStepOverrides;

public class UiStepOverridesTest {
	
	private com.tmobile.ct.codeless.core.Test test;
	
	@BeforeTest
	public void setUp() {
		test = mock(com.tmobile.ct.codeless.core.Test.class);
	    Mockito.when(test.getTestData()).thenReturn(new BasicTestData());
	}
	
	@Test
	public void setAndGetUiStepOverrides() {
		UiTestStep uiTestStep = new UiTestStep();
		uiTestStep.setTestData(Arrays.asList("export::abc::WebElement::getText"));
		UiStep step = new UiStepImpl();
		UiStepOverrides.parseOverrides(test, uiTestStep, step);
		assertEquals(step.getUiStepExportBuilder().size(), 1);
		assertEquals(step.getUiStepExportBuilder().get(0).getSeleniumMethodType(), SeleniumMethodType.WebElement);
	}

}
