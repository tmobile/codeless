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

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class PostmanRequest.
 *
 * @author Rob Graff
 */
public class PostmanRequest {
	
	/** The method. */
	public String method;
	
	/** The header. */
	public List<PostmanHeader> header;
	
	/** The body. */
	public PostmanBody body;
	
	/** The url. */
	public PostmanUrl url;

	/**
	 * Gets the data.
	 *
	 * @param var the var
	 * @return the data
	 */
	public String getData(PostmanVariables var) {
		if (body == null || body.mode == null)  {
			return "";
		} else {
			switch (body.mode) {
				case "raw":
					return var.replace(body.raw);
				case "urlencoded":
					return urlFormEncodeData(var, body.urlencoded);
				default:
					return "";
			}
		}
	}

	/**
	 * Url form encode data.
	 *
	 * @param var the var
	 * @param formData the form data
	 * @return the string
	 */
	public String urlFormEncodeData(PostmanVariables var, List<PostmanUrlEncoded> formData) {
		String result = "";
		int i = 0;
		for (PostmanUrlEncoded encoded : formData) {
			result += encoded.key + "=" + URLEncoder.encode(var.replace(encoded.value));
			if (i < formData.size() - 1) {
				result += "&";
			}
		}
		return result;
	}

	/**
	 * Gets the url.
	 *
	 * @param var the var
	 * @return the url
	 */
	public String getUrl(PostmanVariables var) {
		return var.replace(url.raw);
	}

	/**
	 * Gets the headers.
	 *
	 * @param var the var
	 * @return the headers
	 */
	public Map<String, String> getHeaders(PostmanVariables var) {
		Map<String, String> result = new HashMap<>();
		if (header == null || header.isEmpty()) {
			return result;
		}
		for (PostmanHeader head : header) {
			result.put(head.key, var.replace(head.value));
		}
		return result;
	}
}
