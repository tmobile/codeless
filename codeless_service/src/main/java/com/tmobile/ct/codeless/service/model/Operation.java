package com.tmobile.ct.codeless.service.model;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;

/**
 * The Interface Operation.
 *
 * @author Rob Graff
 */
public interface Operation {

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	HttpMethod getMethod();
	
	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	String getPath();
	
	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	HttpRequest getRequest();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	void setName(String name);
	
	/**
	 * Sets the method.
	 *
	 * @param method the new method
	 */
	void setMethod(HttpMethod method);
	
	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	void setPath(String path);
	
	/**
	 * Sets the request.
	 *
	 * @param request the new request
	 */
	void setRequest(HttpRequest request);
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	OperationKey getKey();
	
	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	Service getService();
	
	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	void setService(Service service);
}
