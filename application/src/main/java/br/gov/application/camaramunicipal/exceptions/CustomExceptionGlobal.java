package br.gov.application.camaramunicipal.exceptions;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.application.camaramunicipal.utils.FactoryMessageErrorUtil;

@ControllerAdvice
public class CustomExceptionGlobal {

    private FactoryMessageErrorUtil message = new FactoryMessageErrorUtil();
    
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> customValidationErrorHanding(Exception e) {
        return new ResponseEntity<>( message.create(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @ExceptionHandler(DefaultException.class)
    protected ResponseEntity<Object> customValidationErrorHanding(DefaultException e) {
        return new ResponseEntity<>( message.create(e.getMessage()), e.getStatusCode() );
    }
    // error handle for @Valid
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> customValidationErrorHanding(BindException e) {

        FieldError fieldError = e.getFieldError();

        if(fieldError != null) {
            return new ResponseEntity<>( message.create(
                fieldError.getField(), fieldError.getDefaultMessage()), HttpStatus.UNPROCESSABLE_ENTITY );
        }

        return new ResponseEntity<>( message.create("", ""), HttpStatus.UNPROCESSABLE_ENTITY );
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> customValidationErrorHanding(AuthenticationException e) {
        return new ResponseEntity<>( message.create(e.getMessage()), HttpStatus.UNAUTHORIZED );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> customValidationErrorHanding(ConstraintViolationException e) {
        return new ResponseEntity<>( message.create(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY );
    }
}