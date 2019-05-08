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

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.ui.action.UiAction;

public class InputModifer implements RequestModifier<String, UiAction> {

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	public InputModifer(String key, TestDataSource dataSource) {
		this.key = key;
		this.dataSource = dataSource;
	}

	@Override
	public void modify(UiAction input,Test test) {
		if(input == null)
			return;
		input.setText((String)dataSource.fullfill());
	}

}
