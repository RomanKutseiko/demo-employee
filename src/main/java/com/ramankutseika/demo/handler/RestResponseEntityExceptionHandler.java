package com.ramankutseika.demo.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    public static final String insufficientValueWarning = "Insufficient value passed!";

    Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = { ConversionFailedException.class, InvalidFormatException.class,
            IllegalArgumentException.class })
    protected ResponseEntity<Object> handleConversionException(RuntimeException ex, WebRequest request) {
        logger.warn(insufficientValueWarning);
        return ResponseEntity.badRequest().body(insufficientValueWarning);
    }
}