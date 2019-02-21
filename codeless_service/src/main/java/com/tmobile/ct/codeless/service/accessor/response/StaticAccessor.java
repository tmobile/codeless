package com.tmobile.ct.codeless.service.accessor.response;

import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class StaticAccessor.
 *
 * @author Rob Graff
 */
public class StaticAccessor implements ResponseAccessor<String>, ServiceAssertionActualProvider<String>{


	/** The value. */
	private String value;

	/**
	 * Instantiates a new static accessor.
	 *
	 * @param value the value
	 */
	public StaticAccessor(String value){
		this.value = value;
	}
	
	/**
	 * Instantiates a new static accessor.
	 */
	public StaticAccessor(){}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public String getActual(ServiceCall call) {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Static Value Ref";
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#read()
	 */
	@Override
	public String read() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#setServiceCall(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public void setServiceCall(ServiceCall call) {
		//do nothing
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#getActual()
	 */
	@Override
	public String getActual() {
		return value;
	}
}
