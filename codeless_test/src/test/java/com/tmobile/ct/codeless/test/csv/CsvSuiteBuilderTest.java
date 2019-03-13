package com.tmobile.ct.codeless.test.csv;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.core.Suite;

public class CsvSuiteBuilderTest {
	
	@Before
	public void setUp() {
        
		CsvConfigTest.setCsvProperties(); 
	}
	
	@Test
	public void itShouldBuildTestSuite() {

		CsvSuiteBuilder builder = new CsvSuiteBuilder();
		Suite suite = builder.build("/suites/ardcsv");
		assertNotNull(suite);
		assertNotNull(suite.getConfig());
		assertNotNull(suite.getTests());
		suite.getTests().forEach(test -> {
			assertNotNull(test.getName());
			test.getSteps().forEach(step -> {
				assertNotNull(step.getName());
			});
		});
	}

}
