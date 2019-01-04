package com.tmobile.ct.codeless.service.assertion;

import java.lang.reflect.Method;

import com.tmobile.ct.codeless.assertion.AssertionBuilder;
import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class BaseServiceCallAssertion.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public class BaseServiceCallAssertion<T> implements ServiceCallAssertion<T>{
	
	/** The assertion. */
	private Assertion<T> assertion;
	
	/** The assertion builder. */
	private AssertionBuilder assertionBuilder;
	
	/** The actual provider. */
	private ServiceAssertionActualProvider<T> actualProvider;
	
	/** The method. */
	private String method;
	
	/** The expected. */
	private T expected;
	
	/** The actual. */
	private T actual;
	
	/** The type class. */
	private Class<T> typeClass;
	
	/** The call. */
	private ServiceCall call;
	
	
	/**
	 * Instantiates a new base service call assertion.
	 *
	 * @param actualProvider the actual provider
	 * @param expected the expected
	 * @param method the method
	 * @param assertionBuilder the assertion builder
	 * @param typeClass the type class
	 * @param call the call
	 */
	public BaseServiceCallAssertion(ServiceAssertionActualProvider<T> actualProvider, T expected, 
			String method, AssertionBuilder assertionBuilder, Class<T> typeClass, ServiceCall call){
		this.expected = expected;
		this.method = method;
		this.assertionBuilder = assertionBuilder;
		this.typeClass = typeClass;
		this.call = call;
		this.actualProvider = actualProvider;
	}
	
	/**
	 * Builds the.
	 */
	private void build(){
		build(call);
	}
	
	/**
	 * Builds the.
	 *
	 * @param call the call
	 */
	private void build(ServiceCall call){
		assertion =  assertionBuilder.build(typeClass);
		actual = actualProvider.getActual(call);
		assertion.setActual(actual);
		assertion.setActualName(actualProvider.getName());
		assertion.setExpected(expected);
		assertion.setMethodName(method);
		assertion.setTypeClass(typeClass);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceCallAssertion#run(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public Boolean run(ServiceCall call) {
		build(call);
		assertion.run();
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		build();
		assertion.run();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceCallAssertion#getActual()
	 */
	@Override
	public T getActual() {
		return actual;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getMethod()
	 */
	@Override
	public Method getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setMethod(java.lang.reflect.Method)
	 */
	@Override
	public void setMethod(Method method) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getMethodName()
	 */
	@Override
	public String getMethodName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setMethodName(java.lang.String)
	 */
	@Override
	public void setMethodName(String name) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getExpected()
	 */
	@Override
	public T getExpected() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getExpectedName()
	 */
	@Override
	public String getExpectedName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getActualName()
	 */
	@Override
	public String getActualName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setExpected(java.lang.Object)
	 */
	@Override
	public void setExpected(T expected) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setExpectedName(java.lang.String)
	 */
	@Override
	public void setExpectedName(String expected) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setActual(java.lang.Object)
	 */
	@Override
	public void setActual(T actual) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setActualName(java.lang.String)
	 */
	@Override
	public void setActualName(String actual) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#fail(java.lang.Throwable)
	 */
	@Override
	public void fail(Throwable e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#getFailureCause()
	 */
	@Override
	public Throwable getFailureCause() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceCallAssertion#setServiceCall(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public void setServiceCall(ServiceCall call) {
		this.call = call;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceCallAssertion#getServiceCall()
	 */
	@Override
	public ServiceCall getServiceCall() {
		return call;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setTypeClass(java.lang.Class)
	 */
	@Override
	public void setTypeClass(Class<T> typeClass) {
		this.typeClass = typeClass;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getTypeClass()
	 */
	@Override
	public Class<T> getTypeClass() {
		return typeClass;
	}

}
