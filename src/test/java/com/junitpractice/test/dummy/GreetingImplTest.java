package com.junitpractice.test.dummy;

import com.junitpractice.dummy.Greeting;
import com.junitpractice.dummy.GreetingImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GreetingImplTest {

    private Greeting greetingInstance;

    @Before
    public void setup(){
        System.out.println("Start test");
        greetingInstance = new GreetingImpl();
    }

    @After
    public void teardown(){
        System.out.println("End test\n\n");
        greetingInstance = null;
    }

    @Test
    public void test(){
        System.out.println("test()");
        assertTrue(true);
    }

    @Test
    public void greetShouldReturnAValidOutput() {
        System.out.println("greetShouldReturnAValidOutput()");
        String greet = greetingInstance.greet("Vova");
        assertNotNull(greet);
        assertEquals("Hello Vova", greet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowException_For_NameIsNull() {
        System.out.println("greetShouldThrowException_For_NameIsNull()");
        greetingInstance.greet(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowException_For_NameIsManySpaces() {
        System.out.println("greetShouldThrowException_For_NameIsManySpaces()");
        greetingInstance.greet("     ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowException_For_NameIsBlank() {
        System.out.println("greetShouldThrowException_For_NameIsBlank()");
        greetingInstance.greet("");
    }




}
