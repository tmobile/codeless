package com.tmobile.ct.codeless.service.model;

/**
 * This class represents individual entities of EndPoint.
 * 
 * @author jchavali
 * 
 */
public class EndPoint {

	private String protocall;
	
	private String host;
	
	private String port;
	
	private String path;

	public String getProtocall() {
		return protocall;
	}

	public void setProtocall(String protocall) {
		this.protocall = protocall;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
