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
package com.tmobile.ct.codeless.service.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.model.swagger.SwaggerReader;
import com.tmobile.ct.codeless.service.restassured.RestAssuredHttpClient;


public class SwaggerParserTest {

//	@Test
	public void itShouldReadPetStoreSwaggerYaml(){
		List<HttpRequest> requests = new SwaggerReader().parse("/model/petstore/swagger.yaml");
		
		assertThat(requests).describedAs("request list").isNotEmpty();
		
		System.out.println("\n\n"+requests);
		
		requests.stream().forEach(req ->{
			if(req.getOperationPath().getValue().equalsIgnoreCase("/store/inventory")){
				Call call = new Call(new RestAssuredHttpClient(), req, 0);
				call.run();
			}
		});
	}
	
	@Test
	public void itShouldReadBankSwaggerYaml(){
		List<HttpRequest> requests = new SwaggerReader().parse("/model/bank/swagger.yaml");
		
		assertThat(requests).describedAs("request list").isNotEmpty();
		
		System.out.println("\n\n"+requests);

		// TODO Replace with mock infrastructure so calls can be made
//		requests.stream().forEach(req ->{
//			
//				Call call = new Call(new RestAssuredHttpClient(), req, 0);
//				new Executor().run(call);
//			
//		});
	}
	
	
}
