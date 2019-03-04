package com.tmobile.ct.codeless.service.model.wsdl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.HttpRequest;

public class WsdlReader {

	public List<String> getOperationByFile(String wsdlFile) {
        List<String> operaList = new ArrayList<>();
        try {
            File file = new File(path(wsdlFile));
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(file);
            Element elementRoot = document.getRootElement();
            Node portTypeNode = elementRoot.selectSingleNode("./*[starts-with(name(),'portType')]");
            List<Node> nodeList = portTypeNode.selectNodes("./*[starts-with(name(),'operation')]");
            for (Node node : nodeList) {
                Element element = (Element) node;
                operaList.add(element.attribute(0).getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return operaList;
    }

	public List<HttpRequest> parse(String resource) throws Exception{
		//WsdlProject project = new WsdlProject();
		/* WsdlInterface[] wsdls = WsdlImporter.importWsdl(project, path(resource),null);
        WsdlInterface wsdl = wsdls[0];*/
		getOperationByFile(resource);
		//parsePath(wsdl);
		return null;
	}

	/*

	List<HttpRequest> requests = new ArrayList<>();

	*//**
	 * Parses the.
	 *
	 * @param resource the resource
	 * @return the list
	 * @throws Exception
	 *//*
	public List<HttpRequest> parse(String resource) throws Exception{
		//WsdlProject project = new WsdlProject();
		 WsdlInterface[] wsdls = WsdlImporter.importWsdl(project, path(resource),null);
        WsdlInterface wsdl = wsdls[0];

		//parsePath(wsdl);
		return null;
	}

	private void parsePath(WsdlInterface wsdl) {
		wsdl.getOperationList().stream().forEach( operation -> {
			parseOperation(operation, wsdl);
		});

	}

	*//**
	 * Parses the operation.
	 *
	 * @param entry the entry
	 * @param path the path
	 * @param name the name
	 * @param swagger the swagger
	 *//*
	private void parseOperation(Operation operation, WsdlInterface wsdl) {
		WsdlOperation oper = (WsdlOperation) operation;
		HttpMethod method = HttpMethod.POST;
		String name = oper.getName();

		HttpRequest req = new HttpRequestImpl();

		req.setHttpMethod(com.tmobile.ct.codeless.service.httpclient.HttpMethod.valueOf(method.name()));
		req.setOperationPath(new OperationPath(name));
		//req.setServicePath(new ServicePath(swagger.getBasePath()));

		//parse host
		//String host = wsdl.getEndpoints()[0];
		String host = "https://bw.apix.plab.internal.t-mobile.com:443/service/soap/v2/CustomerManagement";
		URL url;
		try {
			url = new URL(host);
			if(Optional.ofNullable(url.getPort()).isPresent()){
				req.setPort(url.getPort());
				//host = host.replace(":"+url.getPort(), "");
			}
		} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		req.setHost(new Host(host));

		requests.add(req);

	}

	*//**
	 * Path.
	 *
	 * @param file the file
	 * @return the string
	 *//*




*/
	private String path(String file){
		return ClassPathUtil.getAbsolutePath(file);
	}
}
