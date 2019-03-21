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

import java.util.HashMap;
import java.util.Map;

import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;

/**
 * The Class BasicTestData.
 *
 * @author Rob Graff
 */
public class BasicTestData extends BasicSourcedDataMap implements TestData{

	@Override
	public Map<String, TestDataSource> asMap() {
		HashMap<String,TestDataSource> map = new HashMap<>();
		super.keySet().forEach(key ->{
			map.put(key, super.get(key));
		});
		return map;
	}

	@Override
	public String getValue(String key) {
		return super.get(key).fullfill();
	}

}
