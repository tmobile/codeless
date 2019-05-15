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
package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.testdata.GetTestData;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import java.util.ArrayList;


/**
 * The Class HeaderModifier.
 *
 * @author Rob Graff
 */
public class HeaderModifier implements RequestModifier<Header, HttpRequest> {

	/** The key. */
	private ArrayList<String> key;
	private ArrayList<String> originals;
	/** The dataSource to override. */
	private ArrayList<TestDataSource> dataSource;

	/**
	 * Instantiates a new header modifier.
	 *
	 * @param key the key
	 * @param dataSource dataSource
	 */
	public HeaderModifier(ArrayList key, ArrayList original, ArrayList<TestDataSource> dataSource){
		this.key = key;
		this.dataSource = dataSource;
		this.originals = original;
		}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.
	 * tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request,Test test) {
		GetTestData getTestData = new GetTestData();
		for (int i = 0; i < key.size(); i++) {
			Header newHeader = new Header(key.get(i), getTestData.replaceValueWithTestData(originals.get(i), dataSource));
			request.getHeaders().put(key.get(i), newHeader);
		}
	}
}
