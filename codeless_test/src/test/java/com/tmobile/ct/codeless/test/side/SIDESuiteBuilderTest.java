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

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.test.config.ConfigPropertiesTest;
import com.tmobile.ct.codeless.test.side.SIDESuiteBuilder;
import com.tmobile.ct.codeless.test.side.data.SIDEFileTest;

public class SIDESuiteBuilderTest {
	
	@Before
	public void setUp() {
		
		ConfigPropertiesTest.setProperties(); 
	}
	
	@Test
	public void build() {
		SIDESuiteBuilder sideSuiteBuilder = new SIDESuiteBuilder();
		Assert.assertNotNull(sideSuiteBuilder);
		Suite suite = sideSuiteBuilder.buildSuite(SIDEFileTest.createSideFile());
		Assert.assertNotNull(suite.getTests());
		suite.getTests().forEach(test -> {
			Assert.assertNotNull(test.getSteps());
			Assert.assertNotNull(test.getConfig());
		});
	}

}
