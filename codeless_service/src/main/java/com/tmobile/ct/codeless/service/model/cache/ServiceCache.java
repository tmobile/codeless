package com.tmobile.ct.codeless.service.model.cache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Host;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;
import com.tmobile.ct.codeless.service.model.BasicOperation;
import com.tmobile.ct.codeless.service.model.BasicService;
import com.tmobile.ct.codeless.service.model.Operation;
import com.tmobile.ct.codeless.service.model.Service;
import com.tmobile.ct.codeless.service.model.postman.PostmanParser;
import com.tmobile.ct.codeless.service.model.swagger.SwaggerReader;

/**
 * The Class ServiceCache.
 *
 * @author Rob Graff
 */
public class ServiceCache {

	/** The Constant SWAGGER_YAML. */
	private static final String SWAGGER_YAML = "swagger.yaml";

	/** The Constant POSTMAN_COLLECTION_JSON. */
	private static final String POSTMAN_COLLECTION_JSON = "postman_collection.json";

	/** The cache. */
	private static ConcurrentHashMap<String, Service> cache = new ConcurrentHashMap<>();

	/** The model dir. */
	private static String modelDir;

	/**
	 * Gets the service.
	 *
	 * @param name the name
	 * @return the service
	 */
	public static Service getService(String name, String host, String operation){
		if(!cache.containsKey(name)){
			buildCache(name, host, operation);
		}
		return cache.get(name);
	}

	/**
	 * Adds the service.
	 *
	 * @param name the name
	 * @param service the service
	 */
	public static void addService(String name, Service service){
		cache.put(name, service);
	}

	/**
	 * Builds the cache.
	 *
	 * @param name the name
	 */
	private static void buildCache(String name,String customHost,String customOperation){
		List<HttpRequest> requests = null;
		String basePath = getModelDir() + File.separator + name + File.separator;
		if(ClassPathUtil.exists(basePath+SWAGGER_YAML)){
			requests = new SwaggerReader().parse(basePath+SWAGGER_YAML);
		}else if(ClassPathUtil.exists(basePath+POSTMAN_COLLECTION_JSON)){
			System.out.println("postman api path:: "+basePath+POSTMAN_COLLECTION_JSON);
			requests = new PostmanParser().parse(basePath+POSTMAN_COLLECTION_JSON);
		}

		if(null != requests){
			Service service = new BasicService();
			service.setName(name);
			List<Operation> operations = new ArrayList<>();

			requests.forEach(req -> {
				if(!StringUtils.isEmpty(customHost)) {
					Host host = new Host(customHost.trim());
					req.setHost(host);
				}

				if(!StringUtils.isEmpty(customOperation)) {
					OperationPath overrideOperation = new OperationPath(customOperation.trim());
					req.setOperationPath(overrideOperation);
				}										
								
				String servicePathVal  = "";
				if(req.getServicePath() != null && req.getServicePath().getValue() != null){
				  servicePathVal = req.getServicePath().getValue();				  
				}
				
				Operation operation = new BasicOperation(req.getHttpMethod(),servicePathVal, req.getOperationPath().getValue(), req);
				operations.add(operation);
			});
			service.setOperations(operations);
			cache.put(name, service);
		}

	}

	/**
	 * Gets the model dir.
	 *
	 * @return the model dir
	 */
	private static String getModelDir(){
		if(StringUtils.isBlank(modelDir)){
			modelDir = CodelessConfiguration.getModelDir();
		}
		return modelDir;
	}

	/**
	 * Sets the model dir.
	 *
	 * @param dir the new model dir
	 */
	public static void setModelDir(String dir){
		modelDir = dir;
	}
}
