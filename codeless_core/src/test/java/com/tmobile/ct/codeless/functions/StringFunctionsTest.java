package com.tmobile.ct.codeless.functions;

import org.junit.Test;
import org.testng.Assert;

public class StringFunctionsTest {

    @Test
    public void testStringFunctions(){
        StringFunctions stringFunctions = new StringFunctions("mystring");
        Assert.assertNotNull(stringFunctions.upperCase());
        Assert.assertNotNull(stringFunctions.lowerCase());
        Assert.assertNotNull(stringFunctions.charAt("2"));
        Assert.assertNotNull(stringFunctions.contain("my"));
        Assert.assertNotNull(stringFunctions.compare("otherstring"));
    }
}
