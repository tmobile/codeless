package com.tmobile.ct.codeless.service.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmobile.ct.codeless.service.httpclient.HttpMethod;

/**
 * The Class BasicService.
 *
 * @author Rob Graff
 */
public class BasicService implements Service{

	/** The operations. */
	private Map<OperationKey,Operation> operations = new HashMap<>();
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new basic service.
	 */
	public BasicService(){}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.model.Service#getOperation(com.tmobile.ct.codeless.service.httpclient.HttpMethod, java.lang.String)
	 */
	@Override
	public Operation getOperation(HttpMethod method, String operation) {
		return operations.get(new OperationKey(method,operation));
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.model.Service#addOperation(com.tmobile.ct.codeless.service.model.Operation)
	 */
	@Override
	public void addOperation(Operation operation) {
		operations.put(operation.getKey(), operation);
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.model.Service#setOperations(java.util.List)
	 */
	@Override
	public void setOperations(List<Operation> operations) {
		this.operations.clear();
		operations.stream().forEach(this::addOperation);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.model.Service#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.model.Service#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
}
