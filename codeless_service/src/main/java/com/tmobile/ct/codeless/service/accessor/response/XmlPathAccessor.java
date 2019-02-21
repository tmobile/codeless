package com.tmobile.ct.codeless.service.accessor.response;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;

import io.restassured.path.xml.XmlPath;

/**
 * The Class XmlPathAccessor.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public class XmlPathAccessor<T> implements Accessor<T, ServiceCall>, ServiceAssertionActualProvider<T>{

	/** The xml path. */
	private String xmlPath;

	/** The type class. */
	private Class<T> typeClass;

	/** The call ref. */
	private ServiceCallReference callRef;

	/** The call. */
	private ServiceCall call;

	/**
	 * Instantiates a new xml path accessor.
	 *
	 * @param callRef the call ref
	 * @param xmlPath the xml path
	 * @param typeClass the type class
	 */
	public XmlPathAccessor(ServiceCallReference callRef, String xmlPath, Class<T> typeClass){
		this.xmlPath = xmlPath;
		this.typeClass = typeClass;
		this.typeClass = typeClass;
		this.callRef = callRef;
	}

	/**
	 * Instantiates a new xml path accessor.
	 *
	 * @param xmlPath the xml path
	 * @param typeClass the type class
	 */
	public XmlPathAccessor(String xmlPath, Class<T> typeClass){
		this.xmlPath = xmlPath;
		this.typeClass = typeClass;
		this.typeClass = typeClass;
	}


	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public T getActual(ServiceCall call) {
		return new XmlPath(call.getHttpResponse().getBody().asString()).getObject(xmlPath, typeClass);
	}


	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Response XML: "+xmlPath;
	}


	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#read()
	 */
	@Override
	public T read() {
		return new XmlPath(this.call.getHttpResponse().getBody().asString()).getObject(xmlPath, typeClass);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#setServiceCall(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public void setServiceCall(ServiceCall call) {
		this.call = call;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#getActual()
	 */
	@Override
	public String getActual() {
		return String.valueOf(getActual(this.callRef.find()));
	}

	@Override
	public String value() {
		return xmlPath;
	}

}
