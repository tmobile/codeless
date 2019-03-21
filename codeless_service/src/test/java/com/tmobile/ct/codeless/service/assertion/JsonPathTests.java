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
package com.tmobile.ct.codeless.service.assertion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import io.restassured.path.json.JsonPath;

public class JsonPathTests {

	@Test
	public void jsonPathShouldWorkFromStringJsonField(){
		String json = "{\"a\": \"b\"}";
		JsonPath jsonPath = new JsonPath(json);
		String actual = jsonPath.getString("a");
		System.out.println(actual);
		assertThat(actual).isEqualTo("b");
	}
	
	@Test
	public void jsonPathShouldWorkFromNumberJsonField(){
		String json = "{\"a\": 1}";
		JsonPath jsonPath = new JsonPath(json);
		Long actual = jsonPath.getLong("a");
		Long expected = 1L;
		System.out.println(actual);
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void jsonPathShouldWorkFromBooleanJsonField(){
		String json = "{\"a\": true}";
		JsonPath jsonPath = new JsonPath(json);
		Boolean actual = jsonPath.getBoolean("a");
		Boolean expected = true;
		System.out.println(actual);
		assertThat(actual).isEqualTo(expected);
	}
}
