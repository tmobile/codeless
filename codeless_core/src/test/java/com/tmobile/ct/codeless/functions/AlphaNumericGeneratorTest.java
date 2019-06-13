package com.tmobile.ct.codeless.functions;

import org.junit.Assert;
import org.junit.Test;

public class AlphaNumericGeneratorTest {

    @Test
    public void testAlphaNumericGenerator(){
        AlphaNumericGenerator alphaNumericGenerator = new AlphaNumericGenerator(15);
        Assert.assertNotNull(alphaNumericGenerator.generate());
        alphaNumericGenerator = new AlphaNumericGenerator("DD??");
        Assert.assertNotNull(alphaNumericGenerator.generate());
    }
}
