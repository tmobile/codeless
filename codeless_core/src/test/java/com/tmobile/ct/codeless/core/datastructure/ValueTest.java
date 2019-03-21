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

public class ValueTest {
	@Test
	public void itShouldCreateAsString() {
		Value<String> value = new Value<>();
		value.setValue("Test");
		Assert.assertEquals("Test", value.getValue());
	}

	@Test
	public void itShouldCreateAsObject() {
		Value<Object> value = new Value<>();
		Object test = new Object();
		value.setValue(test);
		Assert.assertEquals(test, value.getValue());
	}

	@Test
	public void itShouldWriteToString() {
		Value<String> value = new Value<>();
		value.setValue("Test");
		Assert.assertEquals("Value [value=Test]", value.toString());
	}
}
