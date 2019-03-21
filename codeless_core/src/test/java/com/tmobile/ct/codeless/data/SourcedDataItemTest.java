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
package com.tmobile.ct.codeless.data;

import org.junit.Test;
import org.junit.Assert;

import com.tmobile.ct.codeless.core.datastructure.SourcedValue;


public class SourcedDataItemTest {
	@Test
	public void itShouldCreateBlank() {
		SourcedDataItem<String,String> di = new SourcedDataItem<>();
		Assert.assertNotNull(di);
	}
	
	@Test
	public void itShouldCreateWithArgs() {
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		SourcedDataItem<String,String> di = new SourcedDataItem<>("Test", value);
		Assert.assertEquals("Test", di.getKey());
		Assert.assertEquals("This is a Test", di.getValue().getValue());
	}
	
	@Test
	public void itShouldGetAndSetKeys() {
		SourcedDataItem<String,String> di = new SourcedDataItem<>();
		di.setKey("Test");
		Assert.assertEquals("Test", di.getKey());
	}
	
	@Test
	public void itShouldGetAndSetValues() {
		SourcedDataItem<String,String> di = new SourcedDataItem<>();
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		di.setValue(value);
		Assert.assertEquals("This is a Test", di.getValue().getValue());
	}	

	@Test
	public void itShouldWriteToString() {
		SourcedDataItem<String,String> di = new SourcedDataItem<>();
		di.setKey("Test");
		SourcedValue<String> value = new SourcedValue<>();
		value.setValue("This is a Test");
		di.setValue(value);
		Assert.assertEquals("SourcedConfigItem [key=Test, value=SourcedValue [source=null, sourceClass=null, value=This is a Test]]", di.toString());
	}
}
