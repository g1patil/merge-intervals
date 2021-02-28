package com.jivanpatil.exception;

/**
 * Error class for invalid input. This is sample error class and more can be created for specific error types.
 * */
public class InvalidInputException extends RuntimeException{
    public InvalidInputException(final String errorMessage){
        super(errorMessage);
    }
}
