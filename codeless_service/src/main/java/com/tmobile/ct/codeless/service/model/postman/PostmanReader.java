package com.tmobile.ct.codeless.service.model.postman;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.ct.codeless.service.model.postman.collection.PostmanCollection;
import com.tmobile.ct.codeless.service.model.postman.collection.PostmanEnvironment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Class PostmanReader.
 *
 * @author Rob Graff
 */
public class PostmanReader {
	
	/** The om. */
	ObjectMapper om;
	
	/**
	 * Instantiates a new postman reader.
	 */
	public PostmanReader() {
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	/**
	 * Read collection file classpath.
	 *
	 * @param fileOnClasspath the file on classpath
	 * @return the postman collection
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PostmanCollection readCollectionFileClasspath(String fileOnClasspath) throws JsonParseException, JsonMappingException, IOException {
		String fileName = fileOnClasspath.substring(fileOnClasspath.indexOf(":")+1);
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		
		PostmanCollection collection = om.readValue(stream, PostmanCollection.class);
		stream.close();
		return collection;
	}
	
	/**
	 * Read environment file classpath.
	 *
	 * @param fileOnClasspath the file on classpath
	 * @return the postman environment
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PostmanEnvironment readEnvironmentFileClasspath(String fileOnClasspath) throws JsonParseException, JsonMappingException, IOException {
		String fileName = fileOnClasspath.substring(fileOnClasspath.indexOf(":")+1);
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		
		PostmanEnvironment env = om.readValue(stream, PostmanEnvironment.class);
		stream.close();
		return env;
	}
	
	/**
	 * Read collection file.
	 *
	 * @param filePath the file path
	 * @return the postman collection
	 * @throws Exception the exception
	 */
	public PostmanCollection readCollectionFile(String filePath) throws Exception {
		if (filePath.startsWith("classpath:")) {
			return readCollectionFileClasspath(filePath);
		}
		InputStream stream = new FileInputStream(new File(filePath));
		PostmanCollection collection = om.readValue(stream, PostmanCollection.class);
		stream.close();
		return collection;
	}

	/**
	 * Read environment file.
	 *
	 * @param filePath the file path
	 * @return the postman environment
	 * @throws Exception the exception
	 */
	public PostmanEnvironment readEnvironmentFile(String filePath) throws Exception {
		if (filePath == null) {
			return new PostmanEnvironment();
		}
		if (filePath.startsWith("classpath:")) {
			return readEnvironmentFileClasspath(filePath);
		}
		InputStream stream = new FileInputStream(new File(filePath));
		PostmanEnvironment env = om.readValue(stream, PostmanEnvironment.class);
		stream.close();
		return env;
	}
}
