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
package com.tmobile.ct.codeless.ui.testdata;

import java.lang.reflect.Method;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;

/**
 * The Class UiVariableExport.
 *
 * @author Sai Chandra Korpu
 */
public class UiVariableExport {

	/** The selenium method. */
	private Method seleniumMethod;

	/** The parameter name. */
	private String parameterName;

	/** The selenium method type. */
	private SeleniumMethodType seleniumMethodType;

	private SourcedDataItem<String, TestDataSource> testDataSource;

	public UiVariableExport(Method seleniumMethod, String parameterName, SeleniumMethodType seleniumMethodType,
			SourcedDataItem<String, TestDataSource> testDataSource) {
		super();
		this.seleniumMethod = seleniumMethod;
		this.parameterName = parameterName;
		this.seleniumMethodType = seleniumMethodType;
		this.testDataSource = testDataSource;
	}

	public SourcedDataItem<String, TestDataSource> getTestDataSource() {
		return testDataSource;
	}

	public void setTestDataSource(SourcedDataItem<String, TestDataSource> testDataSource) {
		this.testDataSource = testDataSource;
	}

	public Method getSeleniumMethod() {
		return seleniumMethod;
	}

	public void setSeleniumMethod(Method seleniumMethod) {
		this.seleniumMethod = seleniumMethod;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public SeleniumMethodType getSeleniumMethodType() {
		return seleniumMethodType;
	}

	public void setSeleniumMethodType(SeleniumMethodType seleniumMethodType) {
		this.seleniumMethodType = seleniumMethodType;
	}

}
