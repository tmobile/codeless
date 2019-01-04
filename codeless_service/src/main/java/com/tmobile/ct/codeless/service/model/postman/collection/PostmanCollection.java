package com.tmobile.ct.codeless.service.model.postman.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class PostmanCollection.
 *
 * @author Rob Graff
 */
public class PostmanCollection {
	
	/** The info. */
	public PostmanInfo info;
	
	/** The item. */
	public List<PostmanItem> item;

	/** The folder lookup. */
	public Map<String, PostmanItem> folderLookup = new HashMap<>();

	/**
	 * Inits the.
	 */
	public void init() {
		for (PostmanItem f : item) {
			folderLookup.put(f.name, f);
		}
	}
}
