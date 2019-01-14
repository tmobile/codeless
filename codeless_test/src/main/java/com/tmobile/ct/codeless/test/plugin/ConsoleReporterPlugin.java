package com.tmobile.ct.codeless.test.plugin;

import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.lifecycle.ExecutionHook;
import com.tmobile.ct.codeless.core.lifecycle.StepHook;
import com.tmobile.ct.codeless.core.lifecycle.SuiteHook;
import com.tmobile.ct.codeless.core.lifecycle.TestHook;

public class ConsoleReporterPlugin implements ExecutionHook, SuiteHook, TestHook, StepHook {

	@Override
	public Class<?> getType() {
		return this.getType();
	}

	@Override
	public void beforeSuite(Suite suite) {
		System.out.println("Before Suite: "+suite.getName());
	}

	@Override
	public void afterSuite(Suite suite) {
		System.out.println("After Suite: "+suite.getName());
	}

	@Override
	public void beforeExecution(Execution execution) {
		System.out.println("Before Execution: "+execution.getStatus());
	}

	@Override
	public void afterExecution(Execution execution) {
		System.out.println("After Execution: "+execution.getStatus());
	}

	@Override
	public void beforeStep(Step step) {
		System.out.println("Before Step: "+step.getName());
	}

	@Override
	public void afterStep(Step step) {
		System.out.println("After Step: "+step.getName());
	}

	@Override
	public void beforeTest(Test test) {
		System.out.println("Before Test: "+test.getName());
	}

	@Override
	public void afterTest(Test test) {
		System.out.println("After Test: "+test.getName());
	}

}
