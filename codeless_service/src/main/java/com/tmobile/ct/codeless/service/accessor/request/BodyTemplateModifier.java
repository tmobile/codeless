package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class BodyTemplateModifier.
 *
 * @author Rob Graff
 */
public class BodyTemplateModifier implements RequestModifier<String, HttpRequest>{

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new body template modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public BodyTemplateModifier(String key, TestDataSource dataSource){
		this.key = key;
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.getBody().setBody(request.getBody().asString().replace(getTemplateKey(key), dataSource.fullfill()));
	}

	/**
	 * Gets the template key.
	 *
	 * @param key the key
	 * @return the template key
	 */
	private String getTemplateKey(String key){
		return "{{"+key+"}}";
	}

}
