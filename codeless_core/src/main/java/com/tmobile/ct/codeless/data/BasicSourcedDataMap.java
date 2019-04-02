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
import java.util.Optional;

import com.tmobile.ct.codeless.core.SourcedData;
import com.tmobile.ct.codeless.core.TestDataSource;


/**
 * The Class BasicSourcedDataMap.
 *
 * @author Rob Graff
 */
public class BasicSourcedDataMap extends HashMap<String, SourcedDataItem<String,TestDataSource>> implements SourcedData{

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.SourcedData#getConfig()
	 */
	@Override
	public synchronized Map<String, SourcedDataItem<String,TestDataSource>> getConfig() {
		return this;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.SourcedData#setConfig(java.util.Map)
	 */
	@Override
	public synchronized void setConfig(Map<String, SourcedDataItem<String,TestDataSource>> config) {
		this.clear();
		this.putAll(config);
	}

	/* (non-Javadoc)
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public SourcedDataItem<String,TestDataSource> put(String key, SourcedDataItem<String,TestDataSource> item) {
		return super.put(key, item);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.SourcedData#get(java.lang.String)
	 */
	@Override
	public TestDataSource get(String key) {
		return super.get(key).getValue().getValue();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.SourcedData#getSourcedValue(java.lang.String)
	 */
	@Override
	public SourcedDataItem<String, TestDataSource> getSourcedValue(String key) {
		return super.get(key);
	}

	@Override
	public Optional<String> getOptional(String key) {
		return Optional.ofNullable(Optional.ofNullable(super.get(key)).map(x -> x.getValue().getValue().fullfill()).orElse(null));
	}
}
