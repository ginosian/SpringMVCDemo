package com.springmvc.demo.exceptions;

/**
 * Created by Martha on 6/29/2016.
 */
public class NoSuchProjectException extends RuntimeException {

    public NoSuchProjectException() {
        super("Specified project does not exist");
    }
}
