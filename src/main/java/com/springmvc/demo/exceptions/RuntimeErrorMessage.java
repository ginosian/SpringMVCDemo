package com.springmvc.demo.exceptions;

/**
 * Created by Martha on 6/27/2016.
 */
public class RuntimeErrorMessage extends RuntimeException {

    private String exceptionMsg;

    public RuntimeErrorMessage(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg(){
        return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
