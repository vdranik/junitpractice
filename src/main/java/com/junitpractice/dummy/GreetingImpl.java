package com.junitpractice.dummy;

public class GreetingImpl implements Greeting {


    public String greet(String name) {

        if(name == null || name.trim().isEmpty()) throw new IllegalArgumentException();

        return "Hello " + name;
    }
}
