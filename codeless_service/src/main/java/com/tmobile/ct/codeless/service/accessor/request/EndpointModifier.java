package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Endpoint;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class EndpointModifier.
 *
 * @author Rob Graff
 */
public class EndpointModifier implements RequestModifier<Endpoint, HttpRequest>{


	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new endpoint modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public EndpointModifier(TestDataSource dataSource){

		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.setEndpoint(new Endpoint(dataSource.fullfill()));

	}
}
