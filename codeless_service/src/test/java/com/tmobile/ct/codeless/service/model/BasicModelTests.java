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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;

public class BasicModelTests {

	HttpRequest req;
	HttpMethod method;
	ServicePath servicePath;
	OperationPath opPath;
	
	@Before
	public void setup(){
		req = mock(HttpRequest.class);
		method = HttpMethod.POST;
		servicePath = new ServicePath("bank");
		opPath = new OperationPath("deposit");
		
		
		when(req.getHttpMethod()).thenReturn(method);
		when(req.getServicePath()).thenReturn(servicePath);
		when(req.getOperationPath()).thenReturn(opPath);
		
	}
	
	@Test
	public void basicModelsShouldInstanciate(){
		BasicOperation op = new BasicOperation(req.getHttpMethod(), req.getServicePath().getValue(), req.getOperationPath().getValue(), req);
		assertThat(op).isNotNull();
		
		BasicService service=  new BasicService();
		
		List<Operation> ops = new ArrayList();
		ops.add(op);
		
		service.setOperations(ops);
		service.addOperation(op);
		
		assertThat(service.getOperation(method, "bankdeposit")).isNotNull();
	}
	
}
