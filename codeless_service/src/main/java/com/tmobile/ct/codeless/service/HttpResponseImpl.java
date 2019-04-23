/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.ct.codeless.service;

/**
 * The Class HttpResponseImpl.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public class HttpResponseImpl<T> extends HttpRequestImpl<T> implements HttpResponse<T> {

	/** The status code. */
	private Integer statusCode;
	
	/** The response time. */
	private Long responseTime;
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpResponse#getStatusCode()
	 */
	@Override
	public Integer getStatusCode() {
		return statusCode;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpResponse#setStatusCode(java.lang.Integer)
	 */
	@Override
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpResponse#getResponseTime()
	 */
	@Override
	public Long getResponseTime() {
		return responseTime;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpResponse#setResponseTime(java.lang.Long)
	 */
	@Override
	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}

	public Boolean isSuccess() {
		return (statusCode / 200) == 1;
	}
}
