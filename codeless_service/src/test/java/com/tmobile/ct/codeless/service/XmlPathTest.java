package com.tmobile.ct.codeless.service;

import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.XmlPathAccessor;
import static org.assertj.core.api.Assertions.assertThat;

import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.restassured.RestAssuredHttpClient;
import org.junit.Before;
import org.junit.Test;

public class XmlPathTest extends BaseWiremockTest{

    XmlPathAccessor accessor;
    Call call;
    @Before
    public void shouldInstantiate() {
        HttpRequest req = buildXmlRequest();
        req.setHttpMethod(HttpMethod.GET);
        call = new Call(new RestAssuredHttpClient(), req, 0);
        call.run();
        accessor = new XmlPathAccessor("message", String.class);
        assertThat(accessor).isNotNull();
    }

    @Test
    public void shouldGetActual(){
        assertThat(accessor.getActual(call)).isEqualTo("testing");
        assertThat(accessor.getActual(call)).isNotEqualTo("othermessage");
    }
}