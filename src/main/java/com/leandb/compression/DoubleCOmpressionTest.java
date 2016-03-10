package com.leandb.compression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by admin on 3/9/16.
 */
public class DoubleCOmpressionTest {


    DoubleCompression doubleCompression;

    @Before
    public void reset() {
     doubleCompression = new DoubleCompression();


    }

    @Test
    public void testLeadingZeroByte() throws IOException {
        int i = doubleCompression.leadingZeroByte((byte) 0xAB);
        Assert.assertEquals(0,i);

        i = doubleCompression.leadingZeroByte((byte) 1);
        Assert.assertEquals(7,i);

        i = doubleCompression.leadingZeroByte((byte) 0xFF);
        Assert.assertEquals(0,i);



    }
    @Test
    public void testtrailingZeroByte() throws IOException {
        int i = doubleCompression.trailingZeroByte((byte) 0xee);
        Assert.assertEquals(1,i);
    }

}
