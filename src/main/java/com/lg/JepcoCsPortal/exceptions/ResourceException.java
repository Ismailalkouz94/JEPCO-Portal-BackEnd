/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lg.JepcoCsPortal.exceptions;

import org.springframework.http.HttpStatus;

/**
 *
 * @author abdallah dabbas
 */
public class ResourceException extends RuntimeException {

    private HttpStatus httpStatus;

    HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ResourceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
