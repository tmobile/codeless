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
 * The Class PostmanEnvironment.
 *
 * @author Rob Graff
 */
public class PostmanEnvironment {
	
	/** The id. */
	public String id;
	
	/** The name. */
	public String name;
	
	/** The values. */
	public List<PostmanEnvValue> values;
	
	/** The timestamp. */
	public Long timestamp;
	
	/** The synced. */
	public Boolean synced;
	
	/** The lookup. */
	public Map<String, PostmanEnvValue> lookup = new HashMap<String, PostmanEnvValue>();
	
	/**
	 * Inits the.
	 */
	public void init() {
		for (PostmanEnvValue val : values) {
			lookup.put(val.key, val);
		}
	}
	
	/**
	 * Sets the environment variable.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setEnvironmentVariable(String key, String value) {
		PostmanEnvValue existingVar = this.lookup.get(key);
		if (existingVar != null) {
			//Update existing value if any
			existingVar.value = value;
		} else {
			PostmanEnvValue newVar = new PostmanEnvValue();
			newVar.key = key;
			newVar.name = "RUNTIME-" + key;
			newVar.type = "text";
			newVar.value = value;
			this.lookup.put(key,  newVar);
		}
	}
}
