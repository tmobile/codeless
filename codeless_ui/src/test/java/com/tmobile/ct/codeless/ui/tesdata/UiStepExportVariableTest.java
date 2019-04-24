package com.tmobile.ct.codeless.ui.tesdata;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;
import com.tmobile.ct.codeless.ui.assertion.UiSeleniumMethod;
import com.tmobile.ct.codeless.ui.testdata.UiStepExportBuilder;
import com.tmobile.ct.codeless.ui.testdata.UiStepExportVariable;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

public class UiStepExportVariableTest {

	private com.tmobile.ct.codeless.core.Test test;

	private WebDriver mockDriver;

	private WebElement mockElement;

	private List<UiStepExportBuilder> uiStepExportBuilder;

	@BeforeTest
	public void init() {
		uiStepExportBuilder = UiStepExportBuilder();
		test = mock(com.tmobile.ct.codeless.core.Test.class);
		mockDriver = mock(WebDriver.class);
		mockElement = mock(WebElement.class);
		Mockito.when(this.mockDriver.getTitle()).thenReturn("title");
		Mockito.when(this.test.getWebDriver()).thenReturn(this.mockDriver);
	}

	@Test
	public void uiStepExportVariableTest() {
		UiStepExportVariable.buildExport(test, uiStepExportBuilder, mockElement);
		UiStepExportBuilder builder = this.uiStepExportBuilder.get(0);
		assertEquals(builder.getTestDataSource().getValue().getValue().fullfill(), "title");
	}

	public List<UiStepExportBuilder> UiStepExportBuilder() {
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setValue(new StaticTestDataSource("key", "value"));
		SourcedDataItem<String, TestDataSource> item = new SourcedDataItem<>("key", value);
		SeleniumMethodType type = SeleniumMethodType.WebDriver;
		Method selMethod;
		try {
			selMethod = getSeleniumMethod("getTitle", "", type);
			UiStepExportBuilder uiStepExportBuilder = new UiStepExportBuilder(selMethod, "", type, item);
			return Arrays.asList(uiStepExportBuilder);
		} catch (NoSuchMethodException e) {
			return null;
		}

	}

	public Method getSeleniumMethod(String methodName, String parameter, SeleniumMethodType type)
			throws NoSuchMethodException {
		return UiSeleniumMethod.getSeleniumMethod(methodName, parameter, type);
	}

}
