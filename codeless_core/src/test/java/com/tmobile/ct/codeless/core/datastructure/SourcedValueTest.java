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

public class SourcedValueTest {
	@Test
	public void itShouldCreate() {
		SourcedValue<String> value = new SourcedValue<>();
		Assert.assertNotNull(value);
	}
	
	@Test
	public void itShouldGetAndSetSource() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setSource(SourcedValueTest.class.getName());
		Assert.assertEquals(SourcedValueTest.class.getName(), value.getSource());
	}
	
	@Test
	public void itShouldGetAndSetSourceClass() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setSourceClass(SourcedValueTest.class);
		Assert.assertEquals(SourcedValueTest.class, value.getSourceClass());
	}
	
	@Test
	public void itShouldGetAndSetValue() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("Test");
		Assert.assertEquals("Test", value.getValue());
	}
	
	@Test
	public void itShouldWriteToString() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setSource(SourcedValueTest.class.getName());
		value.setSourceClass(SourcedValueTest.class);
		value.setValue("Test");
		String expected = "SourcedValue [source=" + value.getSource() + ", sourceClass=" + value.getSourceClass() + ", value=" + value.getValue() + "]";
		Assert.assertEquals(expected, value.toString());
	}
}
