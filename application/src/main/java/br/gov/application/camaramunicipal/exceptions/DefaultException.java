package br.gov.application.camaramunicipal.exceptions;

import org.springframework.http.HttpStatus;

public class DefaultException extends RuntimeException {
    private HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    public DefaultException() { super("Erro interno."); }

    public DefaultException(String message) { super(message); }

    public DefaultException(String message, HttpStatus httpStatus) {
        super(message);
        statusCode = httpStatus;
    }

    public HttpStatus getStatusCode() { return statusCode; }
}