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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tmobile.ct.codeless.service.httpclient.Authentication;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.service.httpclient.Cookies;
import com.tmobile.ct.codeless.service.httpclient.Endpoint;
import com.tmobile.ct.codeless.service.httpclient.Forms;
import com.tmobile.ct.codeless.service.httpclient.Headers;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.httpclient.HttpProtocal;
import com.tmobile.ct.codeless.service.httpclient.MultiParts;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.service.httpclient.PathParams;
import com.tmobile.ct.codeless.service.httpclient.QueryParams;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class HttpRequestImpl.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public class HttpRequestImpl<T> implements HttpRequest<T>, Serializable{

	/** The headers. */
	private Headers headers;

	/** The query params. */
	private QueryParams queryParams;

	/** The path params. */
	private PathParams pathParams;

	/** The cookies. */
	private Cookies cookies;

	/** The multi parts. */
	private MultiParts multiParts;

	/** The forms. */
	private Forms forms;

	/** The body. */
	private Body<T> body;

	/** The auth. */
	private Authentication auth;

	/** The http method. */
	private HttpMethod httpMethod;

	/** The protocal. */
	private HttpProtocal protocal;

	/** The host. */
	private Host host;

	/** The port. */
	private Integer port;

	/** The service path. */
	private ServicePath servicePath;

	/** The operation path. */
	private OperationPath operationPath;

	/** The endpoint. */
	private Endpoint endpoint;

	/** The request modifiers. */
	private List<RequestModifier> requestModifiers = new ArrayList<>();

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setHeaders(com.tmobile.ct.codeless.service.httpclient.Headers)
	 */
	@Override
	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setQueryParams(com.tmobile.ct.codeless.service.httpclient.QueryParams)
	 */
	@Override
	public void setQueryParams(QueryParams queryParams) {
		this.queryParams = queryParams;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setPathParams(com.tmobile.ct.codeless.service.httpclient.PathParams)
	 */
	@Override
	public void setPathParams(PathParams pathParams) {
		this.pathParams = pathParams;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setCookies(com.tmobile.ct.codeless.service.httpclient.Cookies)
	 */
	@Override
	public void setCookies(Cookies cookies) {
		this.cookies = cookies;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setMultiParts(com.tmobile.ct.codeless.service.httpclient.MultiParts)
	 */
	@Override
	public void setMultiParts(MultiParts multiParts) {
		this.multiParts = multiParts;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setForms(com.tmobile.ct.codeless.service.httpclient.Forms)
	 */
	@Override
	public void setForms(Forms forms) {
		this.forms = forms;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setBody(com.tmobile.ct.codeless.service.httpclient.Body)
	 */
	@Override
	public void setBody(Body<T> body) {
		this.body = body;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setAuthentication(com.tmobile.ct.codeless.service.httpclient.Authentication)
	 */
	@Override
	public void setAuthentication(Authentication auth) {
		this.auth = auth;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setHttpMethod(com.tmobile.ct.codeless.service.httpclient.HttpMethod)
	 */
	@Override
	public void setHttpMethod(HttpMethod method) {
		this.httpMethod = method;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setHost(com.tmobile.ct.codeless.service.httpclient.Host)
	 */
	@Override
	public void setHost(Host host) {
		this.host = host;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setPort(java.lang.Integer)
	 */
	@Override
	public void setPort(Integer port){
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setServicePath(com.tmobile.ct.codeless.service.httpclient.ServicePath)
	 */
	@Override
	public void setServicePath(ServicePath servicePath) {
		this.servicePath = servicePath;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setOperationPath(com.tmobile.ct.codeless.service.httpclient.OperationPath)
	 */
	@Override
	public void setOperationPath(OperationPath operationPath) {
		this.operationPath = operationPath;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setEndpoint(com.tmobile.ct.codeless.service.httpclient.Endpoint)
	 */
	@Override
	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getHeaders()
	 */
	@Override
	public Headers getHeaders() {
		return headers;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getQueryParams()
	 */
	@Override
	public QueryParams getQueryParams() {
		return queryParams;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getPathParams()
	 */
	@Override
	public PathParams getPathParams() {
		return pathParams;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getCookies()
	 */
	@Override
	public Cookies getCookies() {
		return cookies;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getMultiParts()
	 */
	@Override
	public MultiParts getMultiParts() {
		return multiParts;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getForms()
	 */
	@Override
	public Forms getForms() {
		return forms;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getBody()
	 */
	@Override
	public Body<T> getBody() {
		return body;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getAuthentication()
	 */
	@Override
	public Authentication getAuthentication() {
		return auth;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getHttpMethod()
	 */
	@Override
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getHost()
	 */
	@Override
	public Host getHost() {
		return host;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getPort()
	 */
	@Override
	public Integer getPort() {
		return port;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getServicePath()
	 */
	@Override
	public ServicePath getServicePath() {
		return servicePath;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getOperationPath()
	 */
	@Override
	public OperationPath getOperationPath() {
		return operationPath;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getEndpoint()
	 */
	@Override
	public Endpoint getEndpoint() {
		return endpoint;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setProtocal(com.tmobile.ct.codeless.service.httpclient.HttpProtocal)
	 */
	@Override
	public void setProtocal(HttpProtocal protocal) {
		this.protocal = protocal;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getProtocal()
	 */
	@Override
	public HttpProtocal getProtocal() {
		return protocal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HttpRequestImpl [headers=").append(headers).append(", queryParams=").append(queryParams)
				.append(", pathParams=").append(pathParams).append(", cookies=").append(cookies).append(", multiParts=")
				.append(multiParts).append(", forms=").append(forms).append(", body=").append(body).append(", auth=")
				.append(auth).append(", httpMethod=").append(httpMethod).append(", protocal=").append(protocal)
				.append(", host=").append(host).append(", port=").append(port).append(", servicePath=")
				.append(servicePath).append(", operationPath=").append(operationPath).append(", endpoint=")
				.append(endpoint).append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#setRequestModifiers(java.util.List)
	 */
	@Override
	public void setRequestModifiers(List<RequestModifier> requestModifiers) {
		this.requestModifiers = requestModifiers;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpRequest#getRequestModifiers()
	 */
	@Override
	public List<RequestModifier> getRequestModifiers() {
		return requestModifiers;
	}
}
