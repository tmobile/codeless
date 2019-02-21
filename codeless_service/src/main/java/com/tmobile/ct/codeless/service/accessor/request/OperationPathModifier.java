package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class OperationPathModifier.
 *
 * @author Rob Graff
 */
public class OperationPathModifier implements RequestModifier<OperationPath, HttpRequest> {

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new operation path modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public OperationPathModifier(TestDataSource dataSource){
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.setOperationPath(new OperationPath(dataSource.fullfill()));

	}
}
