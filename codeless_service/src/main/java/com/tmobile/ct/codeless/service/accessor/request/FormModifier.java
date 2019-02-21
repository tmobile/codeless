package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Form;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class FormModifier.
 *
 * @author Rob Graff
 */
public class FormModifier implements RequestModifier<Form, HttpRequest>{

	/** The key. */
	private String key;

	/** The response accessor. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new form modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public FormModifier(String key, TestDataSource dataSource){
		this.key = key;
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.getForms().put(key, new Form(key, dataSource.fullfill()));

	}
}
