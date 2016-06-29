package com.springmvc.demo.exceptions;

/**
 * Created by Martha on 6/27/2016.
 */
public class NoSuchRoleException extends RuntimeException {

    public NoSuchRoleException() {
        super("Specified role does not exist");
    }

}
