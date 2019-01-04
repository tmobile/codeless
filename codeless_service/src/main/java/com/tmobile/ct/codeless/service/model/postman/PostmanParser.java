package com.tmobile.ct.codeless.service.model.postman;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpRequestImpl;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.service.httpclient.Cookies;
import com.tmobile.ct.codeless.service.httpclient.Endpoint;
import com.tmobile.ct.codeless.service.httpclient.Forms;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.service.httpclient.Headers;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.httpclient.HttpProtocal;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.service.httpclient.PathParams;
import com.tmobile.ct.codeless.service.httpclient.QueryParam;
import com.tmobile.ct.codeless.service.httpclient.QueryParams;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;
import com.tmobile.ct.codeless.service.model.postman.collection.PostmanCollection;
import com.tmobile.ct.codeless.service.model.postman.collection.PostmanItem;

/**
 * The Class PostmanParser.
 *
 * @author Rob Graff
 */
public class PostmanParser {

	/** The requests. */
	List<HttpRequest> requests = new ArrayList<>();
	
	/**
	 * Parses the.
	 *
	 * @param resource the resource
	 * @return the list
	 */
	public List<HttpRequest> parse(String resource){
		
		PostmanReader reader = new PostmanReader();
		
		PostmanCollection collection = null;
		
		try {
			collection = reader.readCollectionFile(ClassPathUtil.getAbsolutePath(resource));
			collection.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		parseCollection(collection);
		
		return requests;
	}
	
	/**
	 * Parses the collection.
	 *
	 * @param collection the collection
	 */
	private void parseCollection(PostmanCollection collection) {
//		collection.item.forEach( folder -> {
		collection.item.forEach( item ->{
				parseRequest(item);
			});;
//		});
		
	}
	
	/**
	 * Parses the request.
	 *
	 * @param item the item
	 */
	private void parseRequest(PostmanItem item) {
		HttpRequest<String> req = new HttpRequestImpl<>();
		
		req.setHttpMethod(HttpMethod.valueOf(item.request.method));
		
		/* TODO postman tracks whole "urls" not host:port/service/operation -> have to parse */
		
		//req.setOperationPath(new OperationPath(name));
		//req.setServicePath(new ServicePath(swagger.getBasePath()));
		
		req.setEndpoint(new Endpoint(item.request.url.raw));
		
		if(CollectionUtils.isNotEmpty(item.request.url.host)){
			req.setHost(new Host(item.request.url.host.get(0)));
		}
		
		req.setPort(Optional.ofNullable(item.request.url.port).map(Integer::valueOf).orElse(null));
		
		if(CollectionUtils.isNotEmpty(item.request.url.path)){
			req.setOperationPath(new OperationPath(item.request.url.path.get(0)));
		}
		
		if(req.getHost() == null && req.getEndpoint() != null){
			try {
				
				if(!req.getEndpoint().getValue().toUpperCase().startsWith("HTTP://") && !req.getEndpoint().getValue().toUpperCase().startsWith("HTTPS://")){
					req.setEndpoint(new Endpoint("http://"+req.getEndpoint().getValue()));
				}
				
				URL url = new URL(req.getEndpoint().getValue());
				req.setProtocal(Optional.ofNullable(url.getProtocol()).map(String::toUpperCase).map(HttpProtocal::valueOf).orElse(HttpProtocal.HTTP));
				req.setHost(new Host(url.getHost()));
				if(url.getPort() > 0){
					req.setPort(url.getPort());
				}
				req.setOperationPath(new OperationPath(url.getPath()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		/* TODO query/path params in the url? Test this */
		QueryParams queryParams = new QueryParams();
		//PathParams pathParams = new PathParams();
		//Forms formParams = new Forms();
		Headers headerParams = new Headers();
		
		/* TODO dont see cookies in postman model.. */
		//Cookies cookieParams = new Cookies();
		
		//query params
		Optional.ofNullable(item.request.url.query).ifPresent(x -> x.forEach( query -> {
			queryParams.put(query.key, new QueryParam(query.key, query.value));
		}));
		
		//headers
		item.request.header.forEach(header ->{
			headerParams.put(header.key, new Header(header.key, header.value));
		});
		
		req.setQueryParams(queryParams);
//		req.setPathParams(pathParams);
		req.setHeaders(headerParams);
//		req.setCookies(cookieParams);
		
		/* TODO test body as string, what about different body types... */
		req.setBody(new Body<String>(item.request.body.raw, String.class));
		
		requests.add(req);
		
	}
}
