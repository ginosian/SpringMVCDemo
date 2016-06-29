package com.springmvc.demo.exceptions;

/**
 * Created by Martha on 6/29/2016.
 */
public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
        super("Specified user does not exist");
    }
}
