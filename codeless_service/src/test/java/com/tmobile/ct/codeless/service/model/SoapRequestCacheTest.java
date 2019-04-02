package com.tmobile.ct.codeless.service.model;

import com.tmobile.ct.codeless.service.model.soap.SoapRequestCache;
import com.tmobile.ct.codeless.service.test.build.ServiceTestStep;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SoapRequestCacheTest {


    @Test
    public void soapRequestCacheShouldInstantiate() throws Exception{
        SoapRequestCache soapRequestCache = new SoapRequestCache();
        assertThat(soapRequestCache).isNotNull();
    }
}
