package br.gov.application.camaramunicipal.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FactoryResponseEntity
{
    public ResponseEntity<?> create(String msg, HttpStatus status)
    {
        var message = new FactoryMessage();
        return new ResponseEntity<>(message.create(msg), status);
    }
}