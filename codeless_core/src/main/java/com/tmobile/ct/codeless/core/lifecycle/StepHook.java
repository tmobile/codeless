package com.tmobile.ct.codeless.core.lifecycle;

import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.plugin.Plugin;

public interface StepHook extends Plugin {

	void beforeStep(Step step);
	
	void afterStep(Step step);
}
