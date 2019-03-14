package com.tmobile.ct.codeless.test.csv;


import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;


public class CsvConfigTest {
	
	@Before
	public void setUp() {
		
		setCsvProperties();
	}

	@Test
	public void getPropertiesTest() {
		
		Config properties = CsvConfig.getCSVProperties();
		assertNotNull(properties);
		StaticTestDataSource ds = (StaticTestDataSource) properties.get("webdriver.runlocal");
		assertNotNull(ds);
		assertEquals(ds.getValue(), "TRUE");
		
	}
	
	public static void setCsvProperties() {
		
		Properties props = new Properties();
		props.setProperty("model.dir", File.separator + "model");
		props.setProperty("suites.dir", File.separator + "suites");
		props.setProperty("webdriver.runlocal", "TRUE");
		CodelessConfiguration.setProperties(props);
	}
}
