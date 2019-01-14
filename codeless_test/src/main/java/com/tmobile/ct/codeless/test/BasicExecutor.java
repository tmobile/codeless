package com.tmobile.ct.codeless.test;

import com.tmobile.ct.codeless.core.Executable;
import com.tmobile.ct.codeless.core.Execution;

public class BasicExecutor {

	public void run(Execution execution){
		execution.getSuites().forEach(suite -> {
			suite.getTests().forEach(test ->{
				test.getSteps().forEach(step ->{
					step.run();
				});
			});
		});
	}
	
	public void run(Executable executable){
		try{
			executable.run();
		}catch(Exception e){
			executable.fail(e);
		}
	}
}
