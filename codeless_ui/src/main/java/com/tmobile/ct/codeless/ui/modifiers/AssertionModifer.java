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
package com.tmobile.ct.codeless.ui.modifiers;

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.testdata.GetTestData;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.testdata.TcdsTestDataSource;
import com.tmobile.ct.codeless.testdata.TestDataHelper;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;

import java.util.ArrayList;

public class AssertionModifer implements RequestModifier<String, UiAssertionBuilder> {

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	private ArrayList<TestDataSource> dataSources;

	public AssertionModifer(String original, ArrayList datasource){
		this.key = original;
		this.dataSources = datasource;
	}

	@Override
	public void modify(UiAssertionBuilder input, Test test) {
		String tcds_value = "";
		if (input == null)
			return;
		GetTestData getTestData = new GetTestData();
		String newVal = getTestData.replaceValueWithTestData(key, dataSources, test);
		input.setExpectedValue(newVal);
	}
}
