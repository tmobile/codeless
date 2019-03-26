package com.tmobile.ct.codeless.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.core.Component;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Retryable;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.Trackable;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.model.Operation;

/**
 * The Class Call.
 *
 * @author Rob Graff
 */
public class Call implements ServiceCall, Step, Trackable, Retryable{

	/** The operation. */
	private Operation operation;
	
	/** The name. */
	private String name;

	/** The request. */
	private HttpRequest request;
	
	/** The response. */
	private HttpResponse response;
	
	/** The client. */
	private HttpClient client;

	/** The result. */
	private Result result;
	
	/** The status. */
	private Status status;

	/** The max retries. */
	private Integer maxRetries;
	
	/** The retries. */
	private Integer retries;
	
	/** The assertions. */
	private List<Assertion> assertions;
	
	/** The failure cause. */
	private Throwable failureCause;
	
	/** The test. */
	private Test test;
	
	/** The is complete. */
	private CompletableFuture<Boolean> isComplete = new CompletableFuture<>();
	
	private Component component;

	/**
	 * Instantiates a new call.
	 *
	 * @param client the client
	 * @param request the request
	 * @param maxRetries the max retries
	 */
	public Call(HttpClient client, HttpRequest request, Integer maxRetries){
		this.client = client;
		this.request = request;
		this.maxRetries = maxRetries;
		this.retries = 0;

		this.status = Status.NO_RUN;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		status = Status.IN_PROGRESS;
		client.build(request);

		while(status != Status.COMPLETE && retries < maxRetries+1){
			try{
				response = client.call();
				validate();
				status = Status.COMPLETE;
				result = Result.PASS;
			}catch(Exception e){
				retries = retries + 1;
				if(retries >= maxRetries){
					status = Status.COMPLETE;
					result = Result.FAIL;
					fail(e);
//					e.printStackTrace();
					throw e;
				}
			}finally{
				markComplete();
			}
		}
	}

	/**
	 * The Enum StepStatus.
	 *
	 * @author Rob Graff
	 */
	private enum StepStatus{
		
		/** The pass. */
		PASS,
/** The fail. */
FAIL,
/** The info. */
INFO
	}

	/**
	 * Log step.
	 *
	 * @param status the status
	 * @param name the name
	 * @param message the message
	 */
	private void logStep(StepStatus status, String name, String message){
		test.getLogProxies().forEach(logger ->{
			logger.log(status.name(), name, message);
		});
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#validate()
	 */
	@Override
	public void validate() {
		if(assertions != null) {
			assertions.forEach(Assertion::run);
		}
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#getResult()
	 */
	@Override
	public Result getResult() {
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#getStatus()
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#setResult(com.tmobile.ct.codeless.core.Result)
	 */
	@Override
	public void setResult(Result result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#setStatus(com.tmobile.ct.codeless.core.Status)
	 */
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Retryable#setMaxRetries(java.lang.Integer)
	 */
	@Override
	public void setMaxRetries(Integer retries) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Retryable#getMaxRetries()
	 */
	@Override
	public Integer getMaxRetries() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Retryable#getRetries()
	 */
	@Override
	public Integer getRetries() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#getHttpRequest()
	 */
	@Override
	public HttpRequest getHttpRequest() {
		return request;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#setHttpRequest(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void setHttpRequest(HttpRequest request) {
		this.request = request;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#getHttpClient()
	 */
	@Override
	public HttpClient getHttpClient() {
		return client;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#setHttpClient(com.tmobile.ct.codeless.service.HttpClient)
	 */
	@Override
	public void setHttpClient(HttpClient client) {
		this.client = client;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#getOperation()
	 */
	@Override
	public Operation getOperation() {
		return operation;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#setOperation(com.tmobile.ct.codeless.service.model.Operation)
	 */
	@Override
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#fail(java.lang.Throwable)
	 */
	@Override
	public void fail(Throwable e) {
		this.failureCause = e;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#getFailureCause()
	 */
	@Override
	public Throwable getFailureCause() {
		return this.failureCause;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#getAssertions()
	 */
	@Override
	public List<Assertion> getAssertions() {
		return assertions;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#setAssertions(java.util.List)
	 */
	@Override
	public void setAssertions(List<Assertion> assertions) {
		this.assertions = assertions;

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#getHttpResponse()
	 */
	@Override
	public HttpResponse getHttpResponse() {
		return response;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#setHttpResponse(com.tmobile.ct.codeless.service.HttpResponse)
	 */
	@Override
	public void setHttpResponse(HttpResponse response) {
		this.response = response;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#getTest()
	 */
	@Override
	public Test getTest() {
		return this.test;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#setTest(com.tmobile.ct.codeless.core.Test)
	 */
	@Override
	public void setTest(Test test) {
		this.test = test;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#isComplete()
	 */
	@Override
	public Future<Boolean> isComplete() {
		return isComplete;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.core.ServiceCall#markComplete()
	 */
	@Override
	public void markComplete() {
		isComplete.complete(true);

	}

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public void setComponent(Component component) {
		this.component = component;
	}
}
