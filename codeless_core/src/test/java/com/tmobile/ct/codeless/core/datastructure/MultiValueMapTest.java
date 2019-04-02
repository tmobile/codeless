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

public class MultiValueMapTest {
	@Test
	public void itShouldCreate() {
		MultiValueMap<String, MultiValue> mvm = new MultiValueMap<>();
		Assert.assertNotNull(mvm);
	}

	@Test
	public void itShouldAdd() {
		MultiValueMap<String, MultiValue> mvm = new MultiValueMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		mvm.add("Test", mv);
		Assert.assertEquals("This is a test", mvm.get("Test").getValues().toArray()[0]);
	}

	@Test
	public void itShouldAddTwoKeys() {
		MultiValueMap<String, MultiValue> mvm = new MultiValueMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		MultiValue<String,String> mv2 = new MultiValue<>("Test", "This is a test");
		mvm.add("Test", mv);
		mvm.add("Test", mv2);
		Assert.assertEquals(2, mvm.get("Test").getValues().toArray().length);
	}

	@Test
	public void itShouldPut() {
		MultiValueMap<String, MultiValue> mvm = new MultiValueMap<>();
		MultiValue<String,String> mv = new MultiValue<>("Test", "This is a test");
		mvm.put("Test", mv);
		Assert.assertEquals("This is a test", mvm.get("Test").getValues().toArray()[0]);
	}
}
