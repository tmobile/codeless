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
