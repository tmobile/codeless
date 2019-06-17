package com.tmobile.ct.codeless.test.config;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;

public class ConfigPropertiesTest {
	
	@Before
	public void setUp() {
		
		setProperties();
	}

	@Test
	public void getPropertiesTest() {
		Map<String, String> properties = ConfigProperties.getProperties();
		assertNotNull(properties);
		String ds = properties.get("webdriver.runlocal");
		assertNotNull(ds);
		assertTrue(ds.equalsIgnoreCase("TRUE"));
		
	}
	
	public static void setProperties() {
		
		Properties props = new Properties();
		props.setProperty("model.dir", File.separator + "model");
		props.setProperty("suites.dir", File.separator + "suites");
		props.setProperty("webdriver.runlocal", "TRUE");
		props.setProperty("testdata.filename", "tesdata");
		CodelessConfiguration.setProperties(props);
	}

}
