package com.codingShuttleSpringWeb.SpringWebTutorial.exceptions;

public class MyResourceNotFoundException extends RuntimeException{

    public MyResourceNotFoundException(String message) {
        super(message);
    }
}
