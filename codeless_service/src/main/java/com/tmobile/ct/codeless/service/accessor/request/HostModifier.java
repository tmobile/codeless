package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class HostModifier.
 *
 * @author Rob Graff
 */
public class HostModifier implements RequestModifier<Host, HttpRequest> {

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new host modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public HostModifier(TestDataSource dataSource) {
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.setHost(new Host(dataSource.fullfill()));

	}
}
