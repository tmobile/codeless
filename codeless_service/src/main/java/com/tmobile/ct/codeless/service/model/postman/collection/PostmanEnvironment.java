package com.tmobile.ct.codeless.service.model.postman.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class PostmanEnvironment.
 *
 * @author Rob Graff
 */
public class PostmanEnvironment {
	
	/** The id. */
	public String id;
	
	/** The name. */
	public String name;
	
	/** The values. */
	public List<PostmanEnvValue> values;
	
	/** The timestamp. */
	public Long timestamp;
	
	/** The synced. */
	public Boolean synced;
	
	/** The lookup. */
	public Map<String, PostmanEnvValue> lookup = new HashMap<String, PostmanEnvValue>();
	
	/**
	 * Inits the.
	 */
	public void init() {
		for (PostmanEnvValue val : values) {
			lookup.put(val.key, val);
		}
	}
	
	/**
	 * Sets the environment variable.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setEnvironmentVariable(String key, String value) {
		PostmanEnvValue existingVar = this.lookup.get(key);
		if (existingVar != null) {
			//Update existing value if any
			existingVar.value = value;
		} else {
			PostmanEnvValue newVar = new PostmanEnvValue();
			newVar.key = key;
			newVar.name = "RUNTIME-" + key;
			newVar.type = "text";
			newVar.value = value;
			this.lookup.put(key,  newVar);
		}
	}
}
