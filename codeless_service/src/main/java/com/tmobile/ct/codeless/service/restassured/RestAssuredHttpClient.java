package com.tmobile.ct.codeless.service.restassured;

import java.util.List;
import java.util.Optional;

import com.tmobile.ct.codeless.service.HttpClient;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpResponse;
import com.tmobile.ct.codeless.testdata.RequestModifier;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * The Class RestAssuredHttpClient.
 *
 * @author Rob Graff
 */
public class RestAssuredHttpClient implements HttpClient{

	/** The req spec builder. */
	private final RequestSpecBuilder reqSpecBuilder;

	/** The req spec. */
	private RequestSpecification reqSpec;

	/** The request. */
	private HttpRequest request;

	/** The response. */
	private HttpResponse response;

	/**
	 * Instantiates a new rest assured http client.
	 */
	public RestAssuredHttpClient(){
		this.reqSpecBuilder = new RequestSpecBuilder();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpClient#build(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void build(HttpRequest request) {
		this.request = request;
		invokeRequestModifiers();
		reqSpec = RestAssuredRequestBuilder.build(request);

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpClient#call()
	 */
	@Override
	public HttpResponse call(){
		response = sendRequest();
		System.err.println("Res Body: "+response.getBody().asString());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpClient#call(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public HttpResponse call(HttpRequest request){
		build(request);
		return call();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpClient#invokeRequestModifiers()
	 */
	@Override
	public void invokeRequestModifiers() {
		if(request == null) return;
		List<RequestModifier> modifiers = request.getRequestModifiers();
		modifiers.forEach(modifier -> modifier.modify(request));

	}

	/**
	 * Send request.
	 *
	 * @return the http response
	 */
	private HttpResponse sendRequest(){
		return Optional.ofNullable(chooseMethod()).map(RestAssuredResponseParser::parse).orElse(null);
	}

	/**
	 * Choose method.
	 *
	 * @return the response
	 */
	private Response chooseMethod(){

		Response response = null;
		String target = "";
		if(request.getOperationPath() != null) {
			target = RestAssuredRequestBuilder.mapOperationPath(request.getOperationPath());
		}
		switch (request.getHttpMethod()){
		case POST:
			/*RestAssured rest = new RestAssured();
			rest.useRelaxedHTTPSValidation();
			HashMap<String,String> headermap = new HashMap<String,String>();
			headermap.put("Content-Type", "text/xml");
			headermap.put("SOAPAction", "\"activateSubscriber\"");
			response = rest.
			given().
			headers(headermap).body(body).
			post("https://bw.apix.plab.internal.t-mobile.com:443/service/soap/v2/CustomerManagement/ProvisionService");//reqSpec.post(target);
			String respBoby = response.body().asString();
			System.out.println("SOAP Response: " + respBoby);*/

			RestAssured rest = new RestAssured();
			rest.useRelaxedHTTPSValidation();
			response = rest.given(reqSpec).post(target);
			String respBoby = response.body().asString();
			break;
		case PUT:
			response = RestAssured.given(reqSpec).put(target);//reqSpec.put(target);
			break;
		case PATCH:
			response = RestAssured.given(reqSpec).patch(target);//reqSpec.patch(target);
			break;
		case GET:
			response = RestAssured.given(reqSpec).get(target);//reqSpec.get(target);
			break;
		case DELETE:
			response = RestAssured.given(reqSpec).delete(target);//reqSpec.delete(target);
			break;
		case OPTIONS:
			response = RestAssured.given(reqSpec).options(target);//reqSpec.options(target);
			break;
		case HEAD:
			response = RestAssured.given(reqSpec).head(target);//reqSpec.head(target);
			break;
		}

		return response;
	}
}
