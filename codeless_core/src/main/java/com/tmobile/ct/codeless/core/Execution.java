package com.tmobile.ct.codeless.core;

import java.util.List;

import com.tmobile.ct.codeless.core.lifecycle.ExecutionHook;
import com.tmobile.ct.codeless.core.lifecycle.StepHook;
import com.tmobile.ct.codeless.core.lifecycle.SuiteHook;
import com.tmobile.ct.codeless.core.lifecycle.TestHook;
import com.tmobile.ct.codeless.core.plugin.Plugin;

public interface Execution extends Trackable, Executable{

	String getId();
	
	Suite getSuite(String id);
	
	List<Suite> getSuites();
	
	void setSuites(List<Suite> suites);
	
	void addSuite(Suite suite);
	
	List<Plugin> getPlugins();
	
	void setPlugins(List<Plugin> plugins);
	
	void addPlugin(Plugin plugin);
	
	List<ExecutionHook> getExecutionHooks();
	
	List<SuiteHook> getSuiteHooks();
	
	List<TestHook> getTestHooks();
	
	List<StepHook> getStepHooks();
}
