package com.tmobile.ct.codeless.service.restassured;

import java.util.Map;
import java.util.Optional;

import com.tmobile.ct.codeless.service.HttpResponse;
import com.tmobile.ct.codeless.service.HttpResponseImpl;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.service.httpclient.Cookie;
import com.tmobile.ct.codeless.service.httpclient.Cookies;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.service.httpclient.Headers;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

/**
 * The Class RestAssuredResponseParser.
 *
 * @author Rob Graff
 */
public class RestAssuredResponseParser {

	/**
	 * Instantiates a new rest assured response parser.
	 */
	private RestAssuredResponseParser(){}
	
	/**
	 * Parses the.
	 *
	 * @param raResponse the ra response
	 * @return the http response
	 */
	public static HttpResponse<String> parse(Response raResponse){
		
		HttpResponse<String> response = new HttpResponseImpl<>();
		
		response.setStatusCode(raResponse.getStatusCode());
		response.setResponseTime(raResponse.getTime());
		
		Optional.ofNullable(raResponse.getHeaders()).map(RestAssuredResponseParser::mapHeaders).ifPresent(response::setHeaders);
		Optional.ofNullable(raResponse.getCookies()).map(RestAssuredResponseParser::mapCookies).ifPresent(response::setCookies);
		Optional.ofNullable(raResponse.getBody()).map(RestAssuredResponseParser::mapBody).ifPresent(response::setBody);
		
		return response;
		
	}
	
	/**
	 * Map body.
	 *
	 * @param body the body
	 * @return the body
	 */
	private static Body<String> mapBody(ResponseBody body){
		return new Body<String>(body.asString(), String.class);
	}
	 
	/**
	 * Map headers.
	 *
	 * @param headers the headers
	 * @return the headers
	 */
	private static Headers mapHeaders(io.restassured.http.Headers headers){
		Headers responseHeaders = new Headers();
		headers.forEach( header -> {
			responseHeaders.put(header.getName(), new Header(header.getName(), header.getValue()));
		});
		return responseHeaders;
	}
	
	/**
	 * Map cookies.
	 *
	 * @param cookies the cookies
	 * @return the cookies
	 */
	private static Cookies mapCookies(Map<String,String> cookies){
		Cookies responseCookies = new Cookies();
		cookies.entrySet().forEach( cookie -> {
			responseCookies.add(cookie.getKey(), new Cookie(cookie.getKey(), cookie.getValue()));
		});
		return responseCookies;
	}
}
