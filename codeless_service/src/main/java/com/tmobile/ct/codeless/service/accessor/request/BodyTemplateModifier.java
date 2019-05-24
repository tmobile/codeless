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
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class BodyTemplateModifier.
 *
 * @author Rob Graff
 */
public class BodyTemplateModifier implements RequestModifier<String, HttpRequest>{

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new body template modifier.
	 *
	 * @param key the key
	 * @param dataSource the response accessor
	 */
	public BodyTemplateModifier(String key, TestDataSource dataSource){
		this.key = key;
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request,Test test) {
		request.getBody().setBody(request.getBody().asString().replace(getTemplateKey(key), (String)dataSource.fullfill()));
	}

	/**
	 * Gets the template key.
	 *
	 * @param key the key
	 * @return the template key
	 */
	private String getTemplateKey(String key){
		return "{{"+key+"}}";
	}

}
