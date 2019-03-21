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
package com.tmobile.ct.codeless.testdata;

import com.tmobile.ct.codeless.core.Accessor;

/**
 * The StaticTestDataSource.
 *
 * @author Fikreselam Elala
 */

import com.tmobile.ct.codeless.core.TestDataSource;

public class StaticTestDataSource implements TestDataSource {

	private String key;
	private String value;
	private Accessor accessor;

	public StaticTestDataSource(String key, String value) {
		super();
		this.value = value;
	}

	@Override
	public String fullfill() {
		return value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public void setAccessor(Accessor accessor) {
		this.accessor = accessor;
	}

	@Override
	public Accessor getAccessor() {
		return accessor;
	}

}
