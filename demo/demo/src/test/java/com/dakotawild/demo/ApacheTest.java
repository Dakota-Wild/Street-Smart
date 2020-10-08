package com.dakotawild.demo;

import java.io.IOException;

import org.apache; 

public class ApacheTest {
    String EXPECTED_STRING = "On https://www.cpp.edu/index.shtml there are 88 different hyperlinks.";

    @Test
    public void test() throws IOException {
        WebController tester = new WebController();
        String result = tester.apache();
        Assert.assertEquals("The CPP site should have 88 hyperlinkes", EXPECTED_STRING, result);
    }
}