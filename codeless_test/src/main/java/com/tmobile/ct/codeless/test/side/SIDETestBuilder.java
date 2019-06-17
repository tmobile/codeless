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
package com.tmobile.ct.codeless.test.side;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestBuilder;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.test.config.CloneConfig;
import com.tmobile.ct.codeless.test.side.data.SIDEStep;
import com.tmobile.ct.codeless.test.side.data.SIDETest;
import com.tmobile.ct.codeless.test.suite.TestImpl;

/**
 * The Class SIDETestBuilder.
 *
 * @author Sai Chandra Korpu
 */
public class SIDETestBuilder implements TestBuilder {

	private static Logger logger = LoggerFactory.getLogger(SIDETestBuilder.class);

	TestImpl test = new TestImpl();

	public Test build(Suite suite, SIDETest sideTest, TestData testData) {
		test.setSuite(suite);
		test.setName(sideTest.getName());
		test.setConfig(CloneConfig.getConfig(suite.getConfig()));
		test.setTestData(testData);

		List<Step> steps = new ArrayList<>();
		sideTest.getCommands().forEach(sideStep -> {
			Step step = buildUiStep(test, sideStep);
			if (step != null) {
				steps.add(step);
			}
		});

		if (steps != null) {
			steps.forEach(step -> {
				step.setTest(test);
				test.addStep(step);
			});
		}

		return test;
	}

	private Step buildUiStep(Test test, SIDEStep sideStep) {

		SIDEStepBuilder seleniumIDEStepBuilder = new SIDEStepBuilder();
		String uiAction = seleniumIDEStepBuilder.getActionType(sideStep.getCommand(), sideStep);
		if (uiAction != null) {
			return seleniumIDEStepBuilder.build(test, sideStep);
		}
		return null;
	}

	@Override
	public Test getTest() {
		return test;
	}

}
