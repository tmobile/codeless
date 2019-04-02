/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.ct.codeless.service.accessor.response;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;

import io.restassured.internal.path.xml.NodeImpl;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

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
	public T getActual(ServiceCall call) {/*xmlPath1.get("eo:sender.eo:senderId.name");*/
		final XmlPath xmlPath1 = XmlPath.with(call.getHttpResponse().getBody().asString());xmlPath1.get("?xml");
		XmlPath path = new XmlPath(call.getHttpResponse().getBody().asString());
		NodeImpl rootNode = null;
		rootNode = path.get("xml");
		String val = "";

		Iterator<Node> iterator = rootNode.children().nodeIterator();
		boolean notFound = true;
		  while (iterator.hasNext() && notFound) {
		    Node node = iterator.next();
		    String value = getXmlPath(node);
		    if(!StringUtils.isEmpty(value)) {
		    	val = value;
		    	notFound = false;
		    }
		  }
		  return (T) val;
	}


	private String getXmlPath(Node node) {

		String value = node.name();
		if(xmlPath.equalsIgnoreCase(value)) {
			return node.value();
		} else if(!node.children().list().isEmpty()) {
			for(Node nod: node.children().list()) {
		    	String name = nod.name();
		    	if(xmlPath.equalsIgnoreCase(name)) {
		    		return nod.value();
		    	}else {
		    		String val = getXmlPath(nod);
		    		if(!StringUtils.isEmpty(val)) {
		    			return val;
		    		}
		    	}
		    }
		}
		return null;
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
