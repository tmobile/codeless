/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.ct.codeless.test.testng;

import java.util.Map;
import java.util.Optional;

import org.testng.ITestResult;
import org.testng.TestNG;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.plugin.ConfigPluginParser;
import com.tmobile.ct.codeless.test.BasicExecution;
import com.tmobile.ct.codeless.test.plugin.PluginBuilder;
import com.tmobile.ct.codeless.test.testng.factory.TestngBuilder;

/**
 * The Class TestngExecution.
 *
 * @author Rob Graff
 */
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
		getSuites().forEach(suite -> {
			parsePlugins(suite.getConfig());
				builder.addSuite(suite, "Codeless Test", "com.tmobile.ct.codeless.test.testng.TestngTest");
		});
		return builder.build();
	}
	
	public void parsePlugins(Map<String, String> config){
		ConfigPluginParser builder = new PluginBuilder();
		Optional.ofNullable(builder.getPlugins(config))
			.ifPresent(x -> x.forEach(plugin -> addPlugin(plugin)));
	}
	
}
