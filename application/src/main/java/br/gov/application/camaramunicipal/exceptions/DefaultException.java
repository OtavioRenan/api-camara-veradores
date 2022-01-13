package br.gov.application.camaramunicipal.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

public class DefaultException extends RuntimeException
{
    @Getter
    @Setter
    private HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    public DefaultException()
    {
        super("Erro interno.");
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
}