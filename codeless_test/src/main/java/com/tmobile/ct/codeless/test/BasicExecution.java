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
package com.tmobile.ct.codeless.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.lifecycle.ExecutionHook;
import com.tmobile.ct.codeless.core.lifecycle.StepHook;
import com.tmobile.ct.codeless.core.lifecycle.SuiteHook;
import com.tmobile.ct.codeless.core.lifecycle.TestHook;
import com.tmobile.ct.codeless.core.plugin.Plugin;
import com.tmobile.ct.codeless.test.plugin.LifecyclePluginFilter;

public class BasicExecution implements Execution{

	protected List<Suite> suites = new ArrayList<>();
	protected HashMap<String, Suite> suiteMap = new HashMap<>();
	protected List<Plugin> plugins = new ArrayList<>();
	
	protected List<ExecutionHook> executionHooks = new ArrayList<>();
	protected List<SuiteHook> suiteHooks = new ArrayList<>();
	protected List<TestHook> testHooks = new ArrayList<>();
	protected List<StepHook> stepHooks = new ArrayList<>();
	
	protected LifecyclePluginFilter pluginFilter = new LifecyclePluginFilter();
	
	protected Result result;
	protected Status status;
	
	protected Throwable failureCause;
	private String id;
	
	public BasicExecution(){
		this.id = UUID.randomUUID().toString();
	}
	
	public BasicExecution(LifecyclePluginFilter pluginFilter){
		this.pluginFilter = pluginFilter;
		this.id = UUID.randomUUID().toString();
	}
	
	private List<Plugin> filterPlugins(Plugin plugin){
		return filterPlugins(Collections.singletonList(plugin));
	}
	
	private List<Plugin> filterPlugins(List<Plugin> plugins){
		plugins = pluginFilter.checkDuplicate(plugins);
		executionHooks = pluginFilter.getExecutionHooks(plugins);
		suiteHooks = pluginFilter.getSuiteHooks(plugins);
		testHooks = pluginFilter.getTestHooks(plugins);
		stepHooks = pluginFilter.getStepHooks(plugins);
		return plugins;
	}
	
	@Override
	public void run() {
		new BasicExecutor().run(this);
	}

	@Override
	public void fail(Throwable e) {
		failureCause = e;
		result = Result.FAIL;
	}

	@Override
	public Throwable getFailureCause() {
		return failureCause;
	}
	
	@Override
	public Result getResult() {
		return result;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public List<Suite> getSuites() {
		return suites;
	}

	@Override
	public void setSuites(List<Suite> suites) {
		suites.forEach(suite -> {
			this.suiteMap.put(suite.getId(), suite);
		});
		this.suites = suites;
	}

	@Override
	public void addSuite(Suite suite) {
		this.suiteMap.put(suite.getId(), suite);
		this.suites.add(suite);
	}

	@Override
	public List<Plugin> getPlugins() {
		return plugins;
	}

	@Override
	public void setPlugins(List<Plugin> plugins) {
		this.plugins = filterPlugins(this.plugins);
		
	}

	@Override
	public void addPlugin(Plugin plugin) {
		this.plugins.addAll(filterPlugins(plugin));
		
	}

	@Override
	public List<ExecutionHook> getExecutionHooks() {
		return executionHooks;
	}

	@Override
	public List<SuiteHook> getSuiteHooks() {
		return suiteHooks;
	}

	@Override
	public List<TestHook> getTestHooks() {
		return testHooks;
	}

	@Override
	public List<StepHook> getStepHooks() {
		return stepHooks;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Suite getSuite(String id) {
		return suiteMap.get(id);
	}

}
