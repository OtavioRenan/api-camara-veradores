package br.gov.application.camaramunicipal.exceptions;

import org.springframework.http.HttpStatus;

public class DefaultException extends RuntimeException
{
    private HttpStatus code;

    public DefaultException()
    {
        super("Erro interno.");
        this.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public DefaultException(String message)
    {
        super(message);
    }

    public DefaultException(String message, HttpStatus httpStatus)
    {
        super(message);
        this.setStatusCode(httpStatus);
    }

    private void setStatusCode(HttpStatus code)
    {
        this.code = code;
    }

    public HttpStatus getStatusCode()
    {
        return this.code;
    }
}