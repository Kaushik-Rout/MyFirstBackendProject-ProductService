package com.pheonix.productservicefirstproject.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Controller advice is a global exception handler which handles all thrown exception from service & controller
@ControllerAdvice
public class GlobalExcectionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handlerArithmaticException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "Arithmatic Exception happened, Calling from controller advice",
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlerNullPointerException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "Nullpointer Exception happened, calling from controller advice",
                HttpStatus.NOT_FOUND
        );
        return response;
    }
}
