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

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * The Class PostmanUrl.
 *
 * @author Rob Graff
 */
public class PostmanUrl {
	
	/** The raw. */
	public String raw;
	
	/** The host. */
	public List<String> host;
	
	/** The port. */
	public String port;
	
	/** The path. */
	public List<String> path;
	
	/** The query. */
	public List<PostmanQueryItem> query;
	
	/**
	 * Instantiates a new postman url.
	 *
	 * @param url the url
	 */
	public PostmanUrl(String url){
		raw = url;
	}
	
	/**
	 * Instantiates a new postman url.
	 */
	public PostmanUrl(){
		
	}
	
	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	@JsonSetter(value="url")
	public void setUrl(String url){
		raw = url;
	}

}
