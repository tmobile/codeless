package com.tmobile.ct.codeless.test.excel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.test.BasicExecutor;


public class ExcelSuiteBuilderTest {

	@Test
	public void itShouldBuildSuite(){
		Suite suite = new ExcelSuiteBuilder().build("/suites/test_google.xlsx");
		System.out.println(suite);
		assertThat(suite.getTests()).describedAs("tests").isNotEmpty();
		System.out.println(suite.getConfig());
		suite.getTests().forEach(test ->{
			System.out.println("\n"+test.getName());
			test.getSteps().forEach(step ->{
			System.out.println(step.getName());
			});
		});
	}
	
	@Test
	public void itShouldRunSuite(){
		Suite suites = new ExcelSuiteBuilder().build("/suites/test_google.xlsx");
		BasicExecutor exec = new BasicExecutor();
			suites.getTests().forEach(test -> 
				test.getSteps().forEach( step ->
					exec.run(step)));
	}
}
