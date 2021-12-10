package br.gov.application.camaramunicipal.utils;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import br.gov.application.camaramunicipal.exceptions.DefaultException;

public class FactoryExceptionNotFund
{
    public void create(Optional<?> model, String message)
    {
        if(!model.isPresent())
        {
            throw new DefaultException(message, HttpStatus.BAD_REQUEST);
        }
    }
}