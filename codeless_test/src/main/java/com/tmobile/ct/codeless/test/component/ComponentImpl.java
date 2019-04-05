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
package com.tmobile.ct.codeless.test.component;

import java.util.ArrayList;
import java.util.List;

import com.tmobile.ct.codeless.core.Component;
import com.tmobile.ct.codeless.core.Step;

public class ComponentImpl implements Component{

	private List<Step> steps = new ArrayList<>();
	private String name = "";
	
	@Override
	public List<Step> getSteps() {
		return steps;
	}

	@Override
	public void addStep(Step step) {
		steps.add(step);
	}

	@Override
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
