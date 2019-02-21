package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.testdata.RequestModifier;


/**
 * The Class BodyModifier.
 *
 * @author Rob Graff
 */
public class BodyModifier implements RequestModifier<Body, HttpRequest>{


	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new body modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public BodyModifier(TestDataSource dataSource){
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.setBody(new Body<String>(dataSource.fullfill(), String.class));

	}

}
