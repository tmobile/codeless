package com.tmobile.ct.codeless.service.accessor.request;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.functions.CheckFunction;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.testdata.GetTestData;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BodyFieldModifier implements RequestModifier<String, HttpRequest> {

    /** The key. */
    private String key;

    private String original;
    /** The dataSource to override. */
    private ArrayList<TestDataSource> dataSources;


    public BodyFieldModifier(String key,String original,  ArrayList<TestDataSource> dataSources){
        this.key = key;
        this.original = original;
        this.dataSources = dataSources;
    }
    @Override
    public void modify(HttpRequest request, Test test) {
        Configuration configuration = Configuration.builder()
                .jsonProvider(new JacksonJsonNodeJsonProvider())
                .mappingProvider(new JacksonMappingProvider())
                .build();
        String body = request.getBody().asString();
        GetTestData getTestData = new GetTestData();
        String newval = getTestData.replaceValueWithTestData(original,dataSources);
        newval = new CheckFunction().parse(newval);
        JsonNode updatedJson = JsonPath.using(configuration).parse(body).set("$."+key,newval).json();
        String resultbody = updatedJson.toString();
        Body newbody = new Body();
        newbody.setBody(resultbody);
        request.setBody(newbody);
    }
}
