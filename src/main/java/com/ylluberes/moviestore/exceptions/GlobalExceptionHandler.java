/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Exception interceptor to handle all incoming bad request.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     *
     * Capture all constraint-events of an incoming request
     * @param exception object that contains not accepted field
     * @param headers headers of the incoming request
     * @param status request status
     * @param request user incoming request
     * @return ResponseEntity<Object> containing BAD_REQUEST
     *
     */
    @Override
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final Map<String,Map<String,String>> body = new LinkedHashMap<>();
        final Map<String, String> propertyErrorPair = new LinkedHashMap<>();

        //Capturing all field-constraint violation pair
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            propertyErrorPair.put(fieldError.getField(),
                                  fieldError.getDefaultMessage());
        });
        body.put("errors", propertyErrorPair);
        return new ResponseEntity<>(body,headers,status);
    }
}