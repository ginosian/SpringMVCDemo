package com.springmvc.demo.exceptions;

/**
 * Created by Martha on 6/27/2016.
 */
public class EmptyRequiredValueException extends RuntimeException {

    public EmptyRequiredValueException() {
        super("Passed value is empty.");
    }
}
