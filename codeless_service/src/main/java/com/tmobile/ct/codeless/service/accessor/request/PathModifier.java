package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.PathParam;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class PathModifier.
 *
 * @author Rob Graff
 */
public class PathModifier implements RequestModifier<PathParam, HttpRequest> {

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new path modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public PathModifier(String key, TestDataSource dataSource){
		this.key = key;
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.getPathParams().put(key, new PathParam(key, dataSource.fullfill()));

	}
}
