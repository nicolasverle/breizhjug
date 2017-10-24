package com.zenika.exemples;

public class Project1Service {

    public String hello(String message) throws IllegalArgumentException {
        if(message != null) {
            return "Hello " + message;
        } else {
            throw new IllegalArgumentException("Message must not be null.");
        }
    }

}
