package br.gov.application.camaramunicipal.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.application.camaramunicipal.utils.FactoryMessageErrorUtil;
import br.gov.application.camaramunicipal.utils.FactoryResponseEntity;

@ControllerAdvice
public class CustomGlobalException {
    private final FactoryResponseEntity response = new FactoryResponseEntity();

    private FactoryMessageErrorUtil message = new FactoryMessageErrorUtil();
    
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> customValidationErrorHanding(Exception e)
    {
        return this.response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> customValidationErrorHanding(ConstraintViolationException e)
    {
        ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();

        return new ResponseEntity<>(this.message.create(violation.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Errors for @Valid
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<?> customValidationErrorHanding(BindException e)
    {
        return new ResponseEntity<>(this.message.create(e.getFieldError().getField(), e.getFieldError().getDefaultMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DefaultException.class)
    protected ResponseEntity<?> customValidationErrorHanding(DefaultException e)
    {
        return this.response.create(e.getMessage(), e.getStatusCode());
    }
}