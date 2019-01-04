package com.tmobile.ct.codeless.assertion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.assertj.core.api.Assertions;

import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.core.Executable;
import com.tmobile.ct.codeless.core.Failable;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Trackable;

/**
 * The Class AssertJAssertion.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public class AssertJAssertion<T> implements Assertion<T>, Executable, Trackable, Failable{

	/** The result. */
	private Result result;
	
	/** The status. */
	private Status status;
	
	/** The throwable. */
	private Throwable throwable;
	
	/** The method name. */
	private String methodName;
	
	/** The method. */
	private Method method;
	
	/** The expected. */
	private T expected;
	
	/** The expected name. */
	private String expectedName;
	
	/** The actual. */
	private T actual;
	
	/** The actual name. */
	private String actualName;
	
	/** The type class. */
	private Class<T> typeClass;

	
	/**
	 * Instantiates a new assert J assertion.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 * @param method the method
	 * @param type the type
	 */
	public AssertJAssertion(T expected, T actual, String method, Class<T> type){
		this.expected = expected;
		this.actual = actual;
		this.methodName = method;
		this.status = Status.NO_RUN;
		
		this.typeClass = type;
	}
	
	/**
	 * Instantiates a new assert J assertion.
	 */
	public AssertJAssertion() {
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		status = Status.IN_PROGRESS;
		
		try {

			// get the assertJ assertion class object -> this is type specific
			Method assertMethod = Assertions.class.getMethod("assertThat", getTypeClass());

			// create a new instance of the assertJ class object, passing in T actual as the constructor param
			Object b = assertMethod.invoke(Assertions.class, actual).getClass().getConstructor(getTypeClass()).newInstance(actual);
			
			// get a list of types implemented/extended from our actual assertion value
			List<Class<?>> members = new ArrayList<>();
			members.add(getTypeClass());
			
			if(ClassUtils.isPrimitiveWrapper(getTypeClass())){
				members.add(ClassUtils.wrapperToPrimitive(getTypeClass()));
			}
			
			members.addAll(Arrays.asList(getTypeClass().getInterfaces()));
			members.addAll(Arrays.asList(getTypeClass().getClasses()));
			members.addAll(Arrays.asList(getTypeClass().getEnclosingClass()));
			members.addAll(Arrays.asList(getTypeClass().getSuperclass()));
			
			
			
			// find the correct assertion method, on our assertJ class, that supports one of the actual value types
			// ex: Long.class -> there is no assertJ method .isGreaterThat(Long) -> there is .isGreaterThan(Comparable)
			// which is an interface that Long.class implements
			Method finalAssertion = null;
			for(Class<?> member : members){
				try{
					finalAssertion = b.getClass().getMethod(methodName, member);
					break;
				}catch(NoSuchMethodException e){
					//do nothing
				}
			}
			
			// if a valid assertion method is found, execute it
			if(finalAssertion != null){
				System.out.println("assert that "+actualName+": "+actual + " "+finalAssertion.getName()+" "+expected);
				finalAssertion.invoke(b, expected);
			}else{
				throw new NoSuchMethodException("invalid assertion method");
			}
			
		}catch(InvocationTargetException ex){
			
			// the underlying assertJ assertion failed, throwing an exception
			// this is a normal assertion failure
			
			throwable = Optional.ofNullable(ExceptionUtils.getRootCause(ex)).orElse(ex);
			AssertionFailureException runEx = new AssertionFailureException(actualName+" "+Optional.ofNullable(throwable.getMessage()).orElse(""));
			runEx.initCause(throwable);
			throw runEx;
			
		} catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InstantiationException ex) {
			
			// there was an error invoking the assertion method
			// the user invoked a non existent assertion method, or the framework failed
			// this is an "error" in trying to assert vs. above catch block is a simple assertion "failure"
			
			throwable = Optional.ofNullable(ExceptionUtils.getRootCause(ex)).orElse(ex);
			
			ex.printStackTrace(); // TODO handle errors vs failures

		}finally{
			if(throwable != null){
				result = Result.FAIL;
			}else{
				result = Result.PASS;
			}
			status = Status.COMPLETE;
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
	 * @see com.tmobile.ct.codeless.core.Failable#fail(java.lang.Throwable)
	 */
	@Override
	public void fail(Throwable e) {
		this.throwable = e;
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getMethod()
	 */
	@Override
	public Method getMethod() {
		return method;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setMethod(java.lang.reflect.Method)
	 */
	@Override
	public void setMethod(Method method) {
		this.method = method;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getExpected()
	 */
	@Override
	public T getExpected() {
		return expected;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getExpectedName()
	 */
	@Override
	public String getExpectedName() {
		return expectedName;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getActual()
	 */
	@Override
	public T getActual() {
		return actual;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getActualName()
	 */
	@Override
	public String getActualName() {
		return actualName;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setExpected(java.lang.Object)
	 */
	@Override
	public void setExpected(T expected) {
		this.expected = expected;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setExpectedName(java.lang.String)
	 */
	@Override
	public void setExpectedName(String expected) {
		this.expectedName = expected;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setActual(java.lang.Object)
	 */
	@Override
	public void setActual(T actual) {
		this.actual = actual;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setActualName(java.lang.String)
	 */
	@Override
	public void setActualName(String actual) {
		this.actualName = actual;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#getFailureCause()
	 */
	@Override
	public Throwable getFailureCause() {
		return throwable;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getTypeClass()
	 */
	@Override
	public Class<T> getTypeClass(){
		return typeClass;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getMethodName()
	 */
	@Override
	public String getMethodName() {
		return methodName;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setMethodName(java.lang.String)
	 */
	@Override
	public void setMethodName(String name) {
		this.methodName = name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#setTypeClass(java.lang.Class)
	 */
	@Override
	public void setTypeClass(Class<T> typeClass) {
		this.typeClass = typeClass;
	}
}
