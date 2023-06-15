package com.example.futsalregisterservice.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceNotFound) {
        super(resourceNotFound);
    }

    public ResourceNotFoundException() {
        super("Resource not found");
    }
}