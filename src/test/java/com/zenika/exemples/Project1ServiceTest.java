package com.zenika.exemples;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Project1ServiceTest {

    private Project1Service service;

    @Before
    public void setUp() {
        service = new Project1Service();
    }

    @Test
    public void shouldSayHelloWorld() {
        String rtn = service.hello("World !");
        Assert.assertTrue("Hello World !".equals(rtn));
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        String rtn;
        try {
            rtn = service.hello(null);
        } catch (IllegalArgumentException e) {
            rtn = e.getMessage();
        }
        Assert.assertTrue("Message must not be null.".equals(rtn));
    }

}
