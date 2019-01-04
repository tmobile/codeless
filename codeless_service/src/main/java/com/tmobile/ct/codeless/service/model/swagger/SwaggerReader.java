package com.tmobile.ct.codeless.service.model.swagger;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpRequestImpl;
import com.tmobile.ct.codeless.service.httpclient.Cookie;
import com.tmobile.ct.codeless.service.httpclient.Cookies;
import com.tmobile.ct.codeless.service.httpclient.Forms;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.service.httpclient.Headers;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.service.httpclient.PathParam;
import com.tmobile.ct.codeless.service.httpclient.PathParams;
import com.tmobile.ct.codeless.service.httpclient.QueryParam;
import com.tmobile.ct.codeless.service.httpclient.QueryParams;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;

import java.util.Optional;

import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.CookieParameter;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.parser.SwaggerParser;

/**
 * The Class SwaggerReader.
 *
 * @author Rob Graff
 */
public class SwaggerReader {

	/** The requests. */
	List<HttpRequest> requests = new ArrayList<>();
	
	/**
	 * Parses the.
	 *
	 * @param resource the resource
	 * @return the list
	 */
	public List<HttpRequest> parse(String resource){
		Swagger swagger = new SwaggerParser().read(path(resource));
		System.out.println(swagger.getPaths());
		
		swagger.getPaths().forEach( (name,path) -> {
			parsePath(name, path, swagger);
		});
		
		return requests;
	}
	
	/**
	 * Parses the path.
	 *
	 * @param name the name
	 * @param path the path
	 * @param swagger the swagger
	 */
	private void parsePath(String name, Path path, Swagger swagger){
		path.getOperationMap().entrySet().forEach(x->parseOperation(x, path, name, swagger));
	}
	
	/**
	 * Parses the operation.
	 *
	 * @param entry the entry
	 * @param path the path
	 * @param name the name
	 * @param swagger the swagger
	 */
	private void parseOperation(Entry<HttpMethod, Operation> entry, Path path, String name, Swagger swagger){
		HttpMethod method = entry.getKey();
		Operation op = entry.getValue();
		HttpRequest req = new HttpRequestImpl();
		
		req.setHttpMethod(com.tmobile.ct.codeless.service.httpclient.HttpMethod.valueOf(method.name()));
		req.setOperationPath(new OperationPath(name));
		req.setServicePath(new ServicePath(swagger.getBasePath()));
		
		//parse host
		String host = swagger.getHost();
		URL url;
		try {
			url = new URL(host);
			if(Optional.ofNullable(url.getPort()).isPresent()){
				req.setPort(url.getPort());
				host = host.replace(":"+url.getPort(), "");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setHost(new Host(host));
		
		QueryParams queryParams = new QueryParams();
		PathParams pathParams = new PathParams();
		Forms formParams = new Forms();
		Headers headerParams = new Headers();
		Cookies cookieParams = new Cookies();
		
		for(Parameter param : op.getParameters()){
			switch(param.getIn()){
			case "query":
				io.swagger.models.parameters.QueryParameter qp = (QueryParameter) param;
				queryParams.put(param.getName(),new QueryParam(param.getName(), String.valueOf(qp.getDefault())));
				break;
			case "path":
				PathParameter pp = (PathParameter) param;
				pathParams.put(param.getName(), new PathParam(param.getName(), (String)pp.getDefault()));
				break;
			case "header":
				HeaderParameter hp = (HeaderParameter) param;
				headerParams.put(param.getName(), new Header(param.getName(), (String) hp.getDefault()));
				break;
			case "cookie":
				CookieParameter cp = (CookieParameter) param;
				cookieParams.put(param.getName(), new Cookie(param.getName(), (String) cp.getDefault()));
			}
		}
		
		req.setQueryParams(queryParams);
		req.setPathParams(pathParams);
		req.setHeaders(headerParams);
		req.setCookies(cookieParams);
		
		requests.add(req);
	}
	
	/**
	 * Path.
	 *
	 * @param file the file
	 * @return the string
	 */
	private String path(String file){
		return ClassPathUtil.getAbsolutePath(file);
	}
}
