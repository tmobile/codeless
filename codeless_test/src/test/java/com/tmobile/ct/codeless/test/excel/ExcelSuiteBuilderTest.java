package com.tmobile.ct.codeless.test.excel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;


import com.tmobile.ct.codeless.core.Suite;


public class ExcelSuiteBuilderTest {

	@Test
	public void itShouldBuildSuite(){
		Suite suite = new ExcelSuiteBuilder().build("/suites/demo_actions.xlsx");
		System.out.println(suite);
		assertThat(suite.getTests()).describedAs("tests").isNotEmpty();
		System.out.println(suite.getConfig());
//		suite.getTests().forEach(test ->{
//			System.out.println("\n"+test.getName());
//			test.getSteps().forEach(step ->{
//				System.out.println(step.getName());
//			});
//		});
	}
	
//	@Test
//	public void itShouldRunSuite(){
//		List<Suite> suites = new ExcelSuiteBuilder().build("/suites/bank2.xlsx");
//		assertThat(suites).describedAs("suites").isNotEmpty();
//		Executor exec = new Executor();
//		suites.forEach( suite -> 
//			suite.getTests().forEach(test -> 
//				test.getSteps().forEach( step ->
//					exec.run(step))));
//	}
}
