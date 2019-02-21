package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class HeaderModifier.
 *
 * @author Rob Graff
 */
public class HeaderModifier implements RequestModifier<Header, HttpRequest>{

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new header modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public HeaderModifier(String key, TestDataSource dataSource){
		this.key = key;
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		Header newHeader = new Header(key, dataSource.fullfill());
		request.getHeaders().put(newHeader.getKey(), newHeader);
	}
}
