package com.dakotawild.streetsmart;

import org.junit.Assert;
import org.junit.Test;

public class GuavaTest {
    String EXPECTED_STRING = "Original list of numbers: 5 4 3 2 1 | Sorted list: 1 2 3 4 5 ";
    public WebController webController;

    public GuavaTest() {
        webController = new WebController();
    }

    @Test
    public void test() {
        String result = webController.guava();
        Assert.assertEquals(result, EXPECTED_STRING);
    }
}
