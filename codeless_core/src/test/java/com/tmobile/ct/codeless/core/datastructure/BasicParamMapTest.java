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
package com.tmobile.ct.codeless.core.datastructure;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


public class BasicParamMapTest {
	@Test
	public void itShouldCreate() {
		BasicParamMap<MultiValue<String,String>> bpm = new BasicParamMap<>();
		Assert.assertNotNull(bpm);
	}
	
	@Test
	public void itShouldPut() {
		BasicParamMap<MultiValue<String,String>> bpm = new BasicParamMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		bpm.put("Test", mv);
		Assert.assertEquals("This is a test", bpm.get("test").getValues().toArray()[0]);
	}
	
	@Test
	public void itShouldWriteToValuesMap() {
		BasicParamMap<MultiValue<String,String>> bpm = new BasicParamMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		bpm.put("Test", mv);
		Map<String,List<String>> valuesMap = bpm.toValuesMap();
		Assert.assertNotNull(valuesMap);
	}
}
