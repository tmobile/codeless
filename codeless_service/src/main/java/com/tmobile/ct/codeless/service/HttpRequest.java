package com.tmobile.ct.codeless.service;

import java.util.List;

import com.tmobile.ct.codeless.service.accessor.request.RequestModifier;
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

/**
 * The Interface HttpRequest.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface HttpRequest<T> {
	
	/**
	 * Sets the request modifiers.
	 *
	 * @param requestModifiers the new request modifiers
	 */
	void setRequestModifiers(List<RequestModifier> requestModifiers);
	
	/**
	 * Gets the request modifiers.
	 *
	 * @return the request modifiers
	 */
	List<RequestModifier> getRequestModifiers();
	
	/**
	 * Sets the headers.
	 *
	 * @param headers the new headers
	 */
	void setHeaders(Headers headers);
	
	/**
	 * Sets the query params.
	 *
	 * @param queryParams the new query params
	 */
	void setQueryParams(QueryParams queryParams);
	
	/**
	 * Sets the path params.
	 *
	 * @param pathParams the new path params
	 */
	void setPathParams(PathParams pathParams);
	
	/**
	 * Sets the cookies.
	 *
	 * @param cookies the new cookies
	 */
	void setCookies(Cookies cookies);
	
	/**
	 * Sets the multi parts.
	 *
	 * @param multiParts the new multi parts
	 */
	void setMultiParts(MultiParts multiParts);
	
	/**
	 * Sets the forms.
	 *
	 * @param forms the new forms
	 */
	void setForms(Forms forms);
	
	/**
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	void setBody(Body<T> body);
	
	/**
	 * Sets the authentication.
	 *
	 * @param auth the new authentication
	 */
	void setAuthentication(Authentication auth);
	
	/**
	 * Sets the http method.
	 *
	 * @param method the new http method
	 */
	void setHttpMethod(HttpMethod method);
	
	/**
	 * Sets the protocal.
	 *
	 * @param protocal the new protocal
	 */
	void setProtocal(HttpProtocal protocal);
	
	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	void setHost(Host host);
	
	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	void setPort(Integer port);
	
	/**
	 * Sets the service path.
	 *
	 * @param servicePath the new service path
	 */
	void setServicePath(ServicePath servicePath);
	
	/**
	 * Sets the operation path.
	 *
	 * @param operationPath the new operation path
	 */
	void setOperationPath(OperationPath operationPath);
	
	/**
	 * Sets the endpoint.
	 *
	 * @param endpoint the new endpoint
	 */
	void setEndpoint(Endpoint endpoint);
	
	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	Headers getHeaders();
	
	/**
	 * Gets the query params.
	 *
	 * @return the query params
	 */
	QueryParams getQueryParams();
	
	/**
	 * Gets the path params.
	 *
	 * @return the path params
	 */
	PathParams getPathParams();
	
	/**
	 * Gets the cookies.
	 *
	 * @return the cookies
	 */
	Cookies getCookies();
	
	/**
	 * Gets the multi parts.
	 *
	 * @return the multi parts
	 */
	MultiParts getMultiParts();
	
	/**
	 * Gets the forms.
	 *
	 * @return the forms
	 */
	Forms getForms();
	
	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	Body<T> getBody();
	
	/**
	 * Gets the authentication.
	 *
	 * @return the authentication
	 */
	Authentication getAuthentication();
	
	/**
	 * Gets the http method.
	 *
	 * @return the http method
	 */
	HttpMethod getHttpMethod();
	
	/**
	 * Gets the protocal.
	 *
	 * @return the protocal
	 */
	HttpProtocal getProtocal();
	
	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	Host getHost();
	
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	Integer getPort();
	
	/**
	 * Gets the service path.
	 *
	 * @return the service path
	 */
	ServicePath getServicePath();
	
	/**
	 * Gets the operation path.
	 *
	 * @return the operation path
	 */
	OperationPath getOperationPath();
	
	/**
	 * Gets the endpoint.
	 *
	 * @return the endpoint
	 */
	Endpoint getEndpoint();
}
