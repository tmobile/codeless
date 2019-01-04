package com.tmobile.ct.codeless.service.model.postman.collection;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * The Class PostmanUrl.
 *
 * @author Rob Graff
 */
public class PostmanUrl {
	
	/** The raw. */
	public String raw;
	
	/** The host. */
	public List<String> host;
	
	/** The port. */
	public String port;
	
	/** The path. */
	public List<String> path;
	
	/** The query. */
	public List<PostmanQueryItem> query;
	
	/**
	 * Instantiates a new postman url.
	 *
	 * @param url the url
	 */
	public PostmanUrl(String url){
		raw = url;
	}
	
	/**
	 * Instantiates a new postman url.
	 */
	public PostmanUrl(){
		
	}
	
	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	@JsonSetter(value="url")
	public void setUrl(String url){
		raw = url;
	}

}
