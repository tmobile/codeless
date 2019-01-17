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
