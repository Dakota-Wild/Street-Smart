package com.dakotawild.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class JsoupTest {
    String EXPECTED_STRING = "On https://www.cpp.edu/index.shtml there are 88 different hyperlinks.";

    @Test
    public void test() throws IOException {
        WebController tester = new WebController();
        String result = tester.jsoup();
        Assert.assertEquals("The CPP site should have 88 hyperlinkes", EXPECTED_STRING, result);
    }
}