package com.springmvc.demo.exceptions;

/**
 * Created by Martha on 6/29/2016.
 */
public class NoSuchTaskException extends RuntimeException {

    public NoSuchTaskException() {
        super("Specified task does not exist");
    }
}
