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
package com.tmobile.ct.codeless.service.model.postman.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class PostmanCollection.
 *
 * @author Rob Graff
 */
public class PostmanCollection {
	
	/** The info. */
	public PostmanInfo info;
	
	/** The item. */
	public List<PostmanItem> item;

	/** The folder lookup. */
	public Map<String, PostmanItem> folderLookup = new HashMap<>();

	/**
	 * Inits the.
	 */
	public void init() {
		for (PostmanItem f : item) {
			folderLookup.put(f.name, f);
		}
	}
}
