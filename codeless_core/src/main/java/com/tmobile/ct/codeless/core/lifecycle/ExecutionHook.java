package com.tmobile.ct.codeless.core.lifecycle;

import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.plugin.Plugin;

public interface ExecutionHook extends Plugin {

	void beforeExecution(Execution execution);
	
	void afterExecution(Execution execution);
	
}
