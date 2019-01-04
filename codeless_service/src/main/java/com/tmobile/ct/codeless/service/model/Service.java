package com.tmobile.ct.codeless.service.model;

import java.util.List;

import com.tmobile.ct.codeless.service.httpclient.HttpMethod;

/**
 * The Interface Service.
 *
 * @author Rob Graff
 */
public interface Service {

	/**
	 * Gets the operation.
	 *
	 * @param method the method
	 * @param operation the operation
	 * @return the operation
	 */
	Operation getOperation(HttpMethod method, String operation);
	
	/**
	 * Adds the operation.
	 *
	 * @param operation the operation
	 */
	void addOperation(Operation operation);
	
	/**
	 * Sets the operations.
	 *
	 * @param operations the new operations
	 */
	void setOperations(List<Operation> operations);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	void setName(String name);
}
