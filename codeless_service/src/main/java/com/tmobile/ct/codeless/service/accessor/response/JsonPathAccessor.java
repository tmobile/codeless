package com.tmobile.ct.codeless.service.accessor.response;

import com.tmobile.ct.codeless.service.HttpResponse;
import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;

import io.restassured.path.json.JsonPath;

/**
 * The Class JsonPathAccessor.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public class JsonPathAccessor<T> implements ResponseAccessor<T>, ServiceAssertionActualProvider<T>{

	/** The json path. */
	private String jsonPath;
	
	/** The type class. */
	private Class<T> typeClass;
	
	/** The call ref. */
	private ServiceCallReference callRef;
	
	/** The call. */
	private ServiceCall call;
	
	/**
	 * Instantiates a new json path accessor.
	 *
	 * @param callRef the call ref
	 * @param jsonPath the json path
	 * @param typeClass the type class
	 */
	public JsonPathAccessor(ServiceCallReference callRef, String jsonPath, Class<T> typeClass){
		this.jsonPath = jsonPath;
		this.typeClass = typeClass;
		this.callRef = callRef;
	}
	
	/**
	 * Instantiates a new json path accessor.
	 *
	 * @param jsonPath the json path
	 * @param typeClass the type class
	 */
	public JsonPathAccessor(String jsonPath, Class<T> typeClass){
		this.jsonPath = jsonPath;
		this.typeClass = typeClass;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public T getActual(ServiceCall call) {
		return new JsonPath(call.getHttpResponse().getBody().asString()).getObject(jsonPath, typeClass);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Response JSON: "+jsonPath;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#read()
	 */
	@Override
	public T read() {
		return new JsonPath(this.call.getHttpResponse().getBody().asString()).getObject(jsonPath, typeClass);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#setServiceCall(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public void setServiceCall(ServiceCall call) {
		this.call = call;
	}


	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#getActual()
	 */
	@Override
	public String getActual() {
		return String.valueOf(getActual(this.callRef.find()));
	}

}
