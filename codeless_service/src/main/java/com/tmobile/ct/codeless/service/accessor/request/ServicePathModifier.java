package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class ServicePathModifier.
 *
 * @author Rob Graff
 */
public class ServicePathModifier implements RequestModifier<ServicePath, HttpRequest>{

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new service path modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public ServicePathModifier(TestDataSource dataSource){
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.setServicePath(new ServicePath(dataSource.fullfill()));

	}
}
