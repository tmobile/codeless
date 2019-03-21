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
package com.tmobile.ct.codeless.ui.page.modals.test;

import org.junit.Assert;
import org.junit.Test;

import com.tmobile.ct.codeless.ui.build.test.UiTestStepTest;
import com.tmobile.ct.codeless.ui.page.modals.CtUiTestRow;
import com.tmobile.ct.codeless.ui.yaml.test.ControlElementTest;

public class CtUiTestRowTest {

	private static CtUiTestRow ctUiTestRow = new CtUiTestRow();

	@Test
	public void testSetAndGetName() {
		String expected = "Name";
		ctUiTestRow.setName(expected);
		Assert.assertEquals(ctUiTestRow.getName(), expected);
	}

	@Test
	public void testExcelUiTestRow() {
		ctUiTestRow.setStep(UiTestStepTest.createExcelUiTestRow());
		Assert.assertNotNull(ctUiTestRow.getStep());
	}

	@Test
	public void testControlElement() {
		ctUiTestRow.setControlElement(ControlElementTest.createControlElement());
		Assert.assertNotNull(ctUiTestRow.getControlElement());
	}

}
