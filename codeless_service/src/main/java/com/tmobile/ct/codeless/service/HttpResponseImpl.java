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

}
