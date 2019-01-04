package com.tmobile.ct.codeless.service.model.postman.collection;

import java.util.List;

/**
 * The Class PostmanItem.
 *
 * @author Rob Graff
 */
public class PostmanItem {
	
	/** The name. */
	public String name;
	
	/** The event. */
	public List<PostmanEvent> event;
	
	/** The request. */
	public PostmanRequest request;
	
	/** The response. */
	public List<String> response;
}
