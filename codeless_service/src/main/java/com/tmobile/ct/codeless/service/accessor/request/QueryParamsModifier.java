package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.QueryParam;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class QueryParamsModifier.
 *
 * @author Rob Graff
 */
public class QueryParamsModifier implements RequestModifier<QueryParam, HttpRequest> {

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new query params modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public QueryParamsModifier(String key, TestDataSource dataSource){
		this.key = key;
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.getQueryParams().put(key, new QueryParam(key, dataSource.fullfill()));
	}
}
