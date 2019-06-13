package com.tmobile.ct.codeless.functions;

import org.junit.Test;
import org.testng.Assert;

public class DOBGeneratorTest {

    @Test
    public void testDOBGenerator(){
        DOBGenerator dobGenerator = new DOBGenerator();
        String result = dobGenerator.generate();
        Assert.assertNotNull(result);
        String exactAge = dobGenerator.generate(30);
        Assert.assertNotNull(exactAge);
    }
}
