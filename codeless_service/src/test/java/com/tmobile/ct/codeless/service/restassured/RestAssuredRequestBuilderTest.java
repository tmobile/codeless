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
package com.tmobile.ct.codeless.service.restassured;

import org.junit.Test;
import org.junit.Assert;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.model.postman.PostmanParser;
import com.tmobile.ct.codeless.service.model.swagger.SwaggerReader;

import io.restassured.specification.RequestSpecification;

import java.util.List;

public class RestAssuredRequestBuilderTest {
	@Test
	public void itShouldBuildFromSwagger() {
		List<HttpRequest> requests = new SwaggerReader().parse("/model/bank/swagger.yaml");
		requests.stream().forEach(req ->{
			RequestSpecification spec = RestAssuredRequestBuilder.build(req);
			Assert.assertNotNull(spec);
		});
	}
	
	@Test
	public void itShouldBuildFromCollection() {
		List<HttpRequest> requests = new PostmanParser().parse("/model/bank/postman_collection.json");
		requests.stream().forEach(req ->{
			RequestSpecification spec = RestAssuredRequestBuilder.build(req);
			Assert.assertNotNull(spec);
		});
	}
}
