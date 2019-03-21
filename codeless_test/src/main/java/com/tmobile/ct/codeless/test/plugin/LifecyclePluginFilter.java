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
package com.tmobile.ct.codeless.test.plugin;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.tmobile.ct.codeless.core.lifecycle.ExecutionHook;
import com.tmobile.ct.codeless.core.lifecycle.StepHook;
import com.tmobile.ct.codeless.core.lifecycle.SuiteHook;
import com.tmobile.ct.codeless.core.lifecycle.TestHook;
import com.tmobile.ct.codeless.core.plugin.Plugin;

public class LifecyclePluginFilter {

	HashSet<String> pluginClasses = new HashSet<>();
	
	public List<Plugin> checkDuplicate(List<Plugin> plugins){
		for(Plugin plugin : plugins){
			if(pluginClasses.contains(plugin.getClass().getName())){
				plugins.remove(plugin);
			}else{
				pluginClasses.add(plugin.getClass().getName());
			}
		}
		return plugins;
	}
	
	public List<ExecutionHook> getExecutionHooks(List<Plugin> plugins){
		return plugins.stream().parallel()
				.filter(x -> isExecutionHook(x))
				.map(x -> (ExecutionHook) x)
				.collect(Collectors.toList());
	}
	
	public List<SuiteHook> getSuiteHooks(List<Plugin> plugins){
		return plugins.stream().parallel()
				.filter(x -> isSuiteHook(x))
				.map(x -> (SuiteHook) x)
				.collect(Collectors.toList());
	}
	
	public List<TestHook> getTestHooks(List<Plugin> plugins){
		return plugins.stream().parallel()
				.filter(x -> isTestHook(x))
				.map(x -> (TestHook) x)
				.collect(Collectors.toList());
	}
	
	public List<StepHook> getStepHooks(List<Plugin> plugins){
		return plugins.stream().parallel()
				.filter(x -> isStepHook(x))
				.map(x -> (StepHook) x)
				.collect(Collectors.toList());
	}
	
	public Boolean isExecutionHook(Plugin plugin){
		return plugin instanceof ExecutionHook;
	}
	
	public Boolean isSuiteHook(Plugin plugin){
		return plugin instanceof SuiteHook;
	}
	
	public Boolean isTestHook(Plugin plugin){
		return plugin instanceof TestHook;
	}
	
	public Boolean isStepHook(Plugin plugin){
		return plugin instanceof StepHook;
	}
}
