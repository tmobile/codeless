package com.tmobile.ct.codeless.test.testng;

import java.util.Optional;

import org.testng.ITestResult;
import org.testng.TestNG;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.plugin.ConfigPluginParser;
import com.tmobile.ct.codeless.test.BasicExecution;
import com.tmobile.ct.codeless.test.plugin.PluginBuilder;
import com.tmobile.ct.codeless.test.testng.factory.TestngBuilder;

public class TestngExecution extends BasicExecution{

	private TestNG testng;
	
	public TestngExecution(){}
	
	@Override
	public void run(){
		try{
			status = Status.NO_RUN;
			testng = buildTestngExecutor();
			status = Status.IN_PROGRESS;
			beforeExecution();
			testng.run();
			parseResult(testng.getStatus());
		}catch(Throwable e){
			fail(e);
			throw e;
		}finally{
			status = Status.COMPLETE;
			afterExecution();
		}
	}
	
	private void afterExecution() {			
		getExecutionHooks().forEach(hook ->{
			hook.afterExecution(this);
		});
	}

	private void beforeExecution() {
		getExecutionHooks().forEach(hook ->{
			hook.beforeExecution(this);
		});
	}

	public void parseResult(int status) {
		if(status == ITestResult.SUCCESS){
			setResult(Result.PASS);
		}else{
			setResult(Result.FAIL);
		}
	}

	public TestNG buildTestngExecutor(){
		TestngBuilder builder = new TestngBuilder();
		getSuites().forEach(suite ->{
			parsePlugins(suite.getConfig());
			builder.addSuite(suite.getName(), "Codeless Test", suite.getId(),
					"com.tmobile.ct.codeless.test.testng.TestngTest",
					suite.getConfig().asMap());
		});
		return builder.build();
	}
	
	public void parsePlugins(Config config){
		ConfigPluginParser builder = new PluginBuilder();
		Optional.ofNullable(builder.getPlugins(config))
			.ifPresent(x -> x.forEach(plugin -> addPlugin(plugin)));
	}
}
