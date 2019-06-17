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
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

public class SIDEFileTest {
	
	private static SIDEFile sideFile = new SIDEFile();
	
	@Test
	public void getSideFile() {
		sideFile = createSideFile();
		Assert.assertNotNull(sideFile);
		Assert.assertNotNull(sideFile.getId());
		Assert.assertNotNull(sideFile.getName());
		Assert.assertNotNull(sideFile.getTests());
		Assert.assertNotNull(sideFile.getSuites());
		Assert.assertNotNull(sideFile.getAdditionalProperties());
	}

	public static SIDEFile createSideFile() {
		
		List<SIDESuite> sideSuites = Arrays.asList(SIDESuiteTest.createSideSuite());

		sideFile.setId("id");
		sideFile.setName("suite");
		sideFile.setTests(SIDETestTest.createListOfSideTests());
		sideFile.setSuites(sideSuites);
		sideFile.setAdditionalProperties(new HashMap<String, Object>());
		return sideFile;
	}

}
