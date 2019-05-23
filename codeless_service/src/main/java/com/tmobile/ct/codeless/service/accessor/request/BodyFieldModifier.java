package com.tmobile.ct.codeless.service.accessor.request;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
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
        String body = request.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> postmanMap;
        GetTestData getTestData = new GetTestData();
        String newval = getTestData.replaceValueWithTestData(original,dataSources);
        String resultbody = "";
        try {
            postmanMap = mapper.readValue(body, Map.class);
            if (postmanMap == null)
                postmanMap = new HashMap<>();

            postmanMap.put(key,newval);
            resultbody = new JSONObject(postmanMap).toJSONString();
            Body newbody = new Body();
            newbody.setBody(resultbody);
            request.setBody(newbody);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
