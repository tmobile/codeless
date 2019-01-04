package com.tmobile.ct.codeless.service.httpclient;

import java.io.File;
import java.util.Map;

/**
 * The Class MultiPart.
 *
 * @author Rob Graff
 */
public class MultiPart {

	/** The content. */
	private File content;

	/** The control name. */
	private String controlName;

	/** The mime type. */
	private String mimeType;

	/** The headers. */
	private Map<String, String> headers;

	/** The charset. */
	private String charset;

	/** The file name. */
	private String fileName;

	/**
	 * Instantiates a new multi part.
	 *
	 * @param content the content
	 * @param controlName the control name
	 * @param mimeType the mime type
	 * @param headers the headers
	 * @param charset the charset
	 * @param fileName the file name
	 */
	public MultiPart(File content, String controlName, String mimeType, Map<String, String> headers, String charset,
			String fileName) {
		this.content = content;
		this.controlName = controlName;
		this.mimeType = mimeType;
		this.headers = headers;
		this.charset = charset;
		this.fileName = fileName;
	}
	
	/**
	 * Instantiates a new multi part.
	 */
	public MultiPart(){}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public File getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the content
	 * @return the multi part
	 */
	public MultiPart setContent(File content) {
		this.content = content;
		return this;
	}

	/**
	 * Gets the control name.
	 *
	 * @return the control name
	 */
	public String getControlName() {
		return controlName;
	}

	/**
	 * Sets the control name.
	 *
	 * @param controlName the control name
	 * @return the multi part
	 */
	public MultiPart setControlName(String controlName) {
		this.controlName = controlName;
		return this;
	}

	/**
	 * Gets the mime type.
	 *
	 * @return the mime type
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Sets the mime type.
	 *
	 * @param mimeType the mime type
	 * @return the multi part
	 */
	public MultiPart setMimeType(String mimeType) {
		this.mimeType = mimeType;
		return this;
	}

	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * Sets the headers.
	 *
	 * @param headers the headers
	 * @return the multi part
	 */
	public MultiPart setHeaders(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}

	/**
	 * Gets the charset.
	 *
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * Sets the charset.
	 *
	 * @param charset the charset
	 * @return the multi part
	 */
	public MultiPart setCharset(String charset) {
		this.charset = charset;
		return this;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the file name
	 * @return the multi part
	 */
	public MultiPart setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	
	
}
