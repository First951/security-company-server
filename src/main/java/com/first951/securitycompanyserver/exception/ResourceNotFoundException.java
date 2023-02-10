package com.first951.securitycompanyserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String name, String field, String value) {
        super(name + " with " + field + "=" + value + " not found");
    }

}