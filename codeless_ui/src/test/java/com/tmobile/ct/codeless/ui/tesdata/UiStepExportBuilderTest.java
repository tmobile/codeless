package com.tmobile.ct.codeless.ui.tesdata;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;
import com.tmobile.ct.codeless.ui.testdata.UiStepExportBuilder;

public class UiStepExportBuilderTest {
	
	@Mock
	private Method seleniumMethod;

	private String parameterName;

	private SeleniumMethodType seleniumMethodType;

	private SourcedDataItem<String, TestDataSource> testDataSource;
	
	private UiStepExportBuilder uiStepExportBuilder;
	
	@BeforeTest
	public void init() {
		parameterName = "";
		seleniumMethodType = SeleniumMethodType.WebDriver;
		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setValue(new StaticTestDataSource("key", "value"));
		SourcedDataItem<String, TestDataSource> item = new SourcedDataItem<>("key", value);
		testDataSource = item;
		uiStepExportBuilder = new UiStepExportBuilder(seleniumMethod, parameterName, seleniumMethodType, testDataSource);
		
	}
	
	@Test
	public void uiStepExportBuilderTest() {
		assertNotNull(uiStepExportBuilder);
		
	}
	
	@Test
	public void setAndGetSeleniumMethodTest() {
		uiStepExportBuilder.setSeleniumMethod(seleniumMethod);
		assertEquals(uiStepExportBuilder.getSeleniumMethod(),seleniumMethod);	
		
	}
	
	@Test
	public void setAndGetSeleniumMethodType() {
		uiStepExportBuilder.setSeleniumMethodType(seleniumMethodType);
		assertEquals(uiStepExportBuilder.getSeleniumMethodType(),seleniumMethodType);
		
	}
	
	@Test
	public void setAndGetTestDataSourceTest() {
		uiStepExportBuilder.setTestDataSource(testDataSource);
		assertNotNull(uiStepExportBuilder.getTestDataSource());
		
	}
	
	@Test
	public void setAndGetParameterName() {
		uiStepExportBuilder.setParameterName(parameterName);
		assertNotNull(uiStepExportBuilder.getParameterName());
		
	}
}
