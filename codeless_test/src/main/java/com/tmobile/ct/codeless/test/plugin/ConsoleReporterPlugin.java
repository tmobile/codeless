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
