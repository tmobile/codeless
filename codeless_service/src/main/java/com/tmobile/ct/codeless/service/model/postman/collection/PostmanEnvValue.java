package com.tmobile.ct.codeless.service.model.postman.collection;

/**
 * The Class PostmanEnvValue.
 *
 * @author Rob Graff
 */
public class PostmanEnvValue {
	
	/** The key. */
	public String key;
	
	/** The value. */
	public String value;
	
	/** The type. */
	public String type;
	
	/** The name. */
	public String name;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "["+key+":"+value+"]";
	}
}
