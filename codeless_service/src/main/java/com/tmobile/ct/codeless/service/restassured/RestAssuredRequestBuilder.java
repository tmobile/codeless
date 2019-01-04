package com.tmobile.ct.codeless.service.restassured;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Authentication;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.service.httpclient.Cookies;
import com.tmobile.ct.codeless.service.httpclient.Endpoint;
import com.tmobile.ct.codeless.service.httpclient.Form;
import com.tmobile.ct.codeless.service.httpclient.Forms;
import com.tmobile.ct.codeless.service.httpclient.Headers;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.service.httpclient.HttpProtocal;
import com.tmobile.ct.codeless.service.httpclient.MultiPart;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.service.httpclient.PathParam;
import com.tmobile.ct.codeless.service.httpclient.PathParams;
import com.tmobile.ct.codeless.service.httpclient.QueryParam;
import com.tmobile.ct.codeless.service.httpclient.QueryParams;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;

/**
 * The Class RestAssuredRequestBuilder.
 *
 * @author Rob Graff
 */
public class RestAssuredRequestBuilder {

	/**
	 * Instantiates a new rest assured request builder.
	 */
	private RestAssuredRequestBuilder(){}
	
	/**
	 * Builds the.
	 *
	 * @param request the request
	 * @return the request specification
	 */
	public static RequestSpecification build(HttpRequest request){
		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
		
		//inject http protocal into host
		try {
			
			if(!request.getHost().getValue().toUpperCase().startsWith("HTTP://") && !request.getHost().getValue().toUpperCase().startsWith("HTTPS://")){
				request.setHost(new Host("http://"+request.getHost().getValue()));
			}
			
			URL url = new URL(request.getHost().getValue());
			if(!Optional.ofNullable(url.getProtocol()).isPresent()){
				String host = Optional.ofNullable(request.getProtocal()).map(HttpProtocal::name).orElse(HttpProtocal.HTTP.name()) + request.getHost().getValue();
				request.setHost(new Host(host));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		Optional.ofNullable(request.getHeaders()).map(RestAssuredRequestBuilder::mapHeaders).ifPresent(reqSpecBuilder::addHeaders);
		Optional.ofNullable(request.getQueryParams()).map(RestAssuredRequestBuilder::mapQueryParams).ifPresent(reqSpecBuilder::addQueryParams);
		Optional.ofNullable(request.getPathParams()).map(RestAssuredRequestBuilder::mapPathParams).ifPresent(reqSpecBuilder::addPathParams);
		Optional.ofNullable(request.getCookies()).map(RestAssuredRequestBuilder::mapCookies).ifPresent(reqSpecBuilder::addCookies);
		Optional.ofNullable(request.getForms()).map(RestAssuredRequestBuilder::mapForms).ifPresent(reqSpecBuilder::addFormParams);
		Optional.ofNullable(request.getBody()).map(RestAssuredRequestBuilder::mapBody).ifPresent(reqSpecBuilder::setBody);
		Optional.ofNullable(request.getAuthentication()).map(RestAssuredRequestBuilder::mapAuthentication).ifPresent(reqSpecBuilder::setAuth);
		Optional.ofNullable(request.getHost()).map(RestAssuredRequestBuilder::mapHost).ifPresent(reqSpecBuilder::setBaseUri);
		Optional.ofNullable(request.getPort()).ifPresent(reqSpecBuilder::setPort);
		Optional.ofNullable(request.getServicePath()).map(RestAssuredRequestBuilder::mapServicePath).ifPresent(reqSpecBuilder::setBasePath);
		Optional.ofNullable(request.getMultiParts()).ifPresent(x -> x.stream().forEach( part -> reqSpecBuilder.addMultiPart(mapMultiPart(part))));
		
		
		
		reqSpecBuilder.log(LogDetail.ALL);
//		reqSpecBuilder.addFilter(new ResponseLoggingFilter());
		
		return reqSpecBuilder.build();
	}
	
	/**
	 * Map body.
	 *
	 * @param <T> the generic type
	 * @param body the body
	 * @return the t
	 */
	public static <T> T mapBody(Body<T> body){
		return body.getBody();
	}
	
	/**
	 * Map operation path.
	 *
	 * @param operationPath the operation path
	 * @return the string
	 */
	public static String mapOperationPath(OperationPath operationPath) {
		return operationPath.getValue();
	}

	/**
	 * Map service path.
	 *
	 * @param servicePath the service path
	 * @return the string
	 */
	private static String mapServicePath(ServicePath servicePath) {
		return servicePath.getValue();
	}

	/**
	 * Map host.
	 *
	 * @param host the host
	 * @return the string
	 */
	private static String mapHost(Host host) {
		return host.getValue();
	}

	/**
	 * Map authentication.
	 *
	 * @param authentication the authentication
	 * @return the authentication scheme
	 */
	private static AuthenticationScheme mapAuthentication(Authentication authentication) {
		// TODO auth type implementations
		return null;
	}

	/**
	 * Map forms.
	 *
	 * @param forms the forms
	 * @return the map
	 */
	private static Map<String, ?> mapForms(Forms forms) {
		return forms.toValuesMap();
	}

	/**
	 * Map multi part.
	 *
	 * @param multiPart the multi part
	 * @return the multi part specification
	 */
	private static MultiPartSpecification mapMultiPart(MultiPart multiPart) {
		return new MultiPartSpecBuilder(multiPart.getContent())
				.fileName(multiPart.getFileName())
				.mimeType(multiPart.getMimeType())
				.controlName(multiPart.getControlName())
				.charset(multiPart.getCharset())
				.headers(multiPart.getHeaders())
				.build();
	}

	/**
	 * Map path params.
	 *
	 * @param pathParams the path params
	 * @return the map
	 */
	private static Map<String, ?> mapPathParams(PathParams pathParams) {
		return pathParams.toValuesMap();
	}

	/**
	 * Map query params.
	 *
	 * @param queryParams the query params
	 * @return the map
	 */
	private static Map<String, ?> mapQueryParams(QueryParams queryParams) {
		return queryParams.toValuesMap();
	}

	/**
	 * Map headers.
	 *
	 * @param headers the headers
	 * @return the map
	 */
	private static Map<String,String> mapHeaders(Headers headers) {
		Map<String,String> map = new HashMap<>();
		headers.keySet().stream().forEach(x -> {
			map.put(x, headers.get(x).getValues().get(0));
		});
		return map;
	}

	/**
	 * Map cookies.
	 *
	 * @param cookies the cookies
	 * @return the io.restassured.http. cookies
	 */
	private static io.restassured.http.Cookies mapCookies(Cookies cookies){
		// TODO - cookies mapping
		return null;
	}
}
