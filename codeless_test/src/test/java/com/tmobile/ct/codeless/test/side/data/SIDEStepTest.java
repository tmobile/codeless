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
package com.tmobile.ct.codeless.test.side.data;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SIDEStepTest {

	private static SIDEStep sideStep = new SIDEStep();

	@Test
	public void testSetandGetId() {
		String expected = "id";
		sideStep.setId(expected);
		Assert.assertEquals(expected, sideStep.getId());
	}

	@Test
	public void testSetandGetCommand() {
		String expected = "click";
		sideStep.setCommand(expected);
		Assert.assertEquals(expected, sideStep.getCommand());
	}

	@Test
	public void testSetAndGetComment() {
		String expected = "StepName";
		sideStep.setComment(expected);
		Assert.assertEquals(expected, sideStep.getComment());
	}

	@Test
	public void testSetAndGetTarget() {
		String expected = "name=q";
		sideStep.setTarget(expected);
		Assert.assertEquals(expected, sideStep.getTarget());
	}

	@Test
	public void testSetAndGetTargets() {
		List<List<String>> expected = Arrays.asList(Arrays.asList("a", "b", "c"));
		sideStep.setTargets(expected);
		Assert.assertEquals(expected.size(), sideStep.getTargets().size());
	}
	
	public static SIDEStep createSideStep() {
		
		SIDEStep sideStep = new SIDEStep();
		List<List<String>> expected = Arrays.asList(Arrays.asList("a", "b", "c"));
		sideStep.setId("id");
		sideStep.setCommand("click");
		sideStep.setComment("stepName");
		sideStep.setTarget("target");
		sideStep.setTargets(expected);
		sideStep.setValue("value");
		return sideStep;
	}
	
	public static List<SIDEStep> createListOfSideSteps() {
		SIDEStep step =  createSideStep();
		step.setCommand("assertText");
		return Arrays.asList(createSideStep(),step);
	}

}
