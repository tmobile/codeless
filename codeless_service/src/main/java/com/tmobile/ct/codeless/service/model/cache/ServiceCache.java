package com.tmobile.ct.codeless.service.model.cache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.HttpRequest;
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

	/** The Constant WSDL Defination. */
	private static final String WSDL_XML = "wsdlFile.wsdl";

	/** The cache. */
	private static ConcurrentHashMap<String, Service> cache = new ConcurrentHashMap<>();

	/** The model dir. */
	private static String modelDir;

	/**
	 * Gets the service.
	 *
	 * @param name the name
	 * @return the service
	 * @throws Exception
	 */
	public static Service getService(String name){
		if(!cache.containsKey(name)){
			buildCache(name);
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
	 * @throws Exception
	 */
	private static void buildCache(String name) {
		List<HttpRequest> requests = null;
		String basePath = getModelDir() + File.separator + name + File.separator;
		if(ClassPathUtil.exists(basePath+SWAGGER_YAML)){
			requests = new SwaggerReader().parse(basePath+SWAGGER_YAML);
		}else if(ClassPathUtil.exists(basePath+POSTMAN_COLLECTION_JSON)){
			System.out.println("postman api path:: "+basePath+POSTMAN_COLLECTION_JSON);
			requests = new PostmanParser().parse(basePath+POSTMAN_COLLECTION_JSON);
		} /*else if(ClassPathUtil.exists(basePath+WSDL_XML)) {
			System.out.println("wsdl api path:: "+basePath+WSDL_XML);
			try {
				requests = new WsdlReader().parse(basePath+WSDL_XML);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/

		if(null != requests){
			Service service = new BasicService();
			service.setName(name);
			List<Operation> operations = new ArrayList<>();

			requests.forEach(req -> {

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
