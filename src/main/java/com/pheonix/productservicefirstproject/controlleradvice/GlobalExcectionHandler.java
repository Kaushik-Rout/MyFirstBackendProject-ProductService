package com.pheonix.productservicefirstproject.controlleradvice;

import com.pheonix.productservicefirstproject.dtos.ExceptionDtos;
import com.pheonix.productservicefirstproject.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

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

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handlerFileNotFoundException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "Nullpointer Exception happened, calling from controller advice",
                HttpStatus.NOT_FOUND
        );
        return response;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDtos> handlerNullPointerException(){
        ExceptionDtos exceptionDtos = new ExceptionDtos();
        exceptionDtos.setMessage("File not found, sending from controller advice");
        exceptionDtos.setSolution("I have no idea about solution, please try again");
        ResponseEntity<ExceptionDtos> response = new ResponseEntity<>(exceptionDtos, HttpStatus.NOT_FOUND);

        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDtos> handlerProductNotFoundException(){
        ExceptionDtos exceptionDtos = new ExceptionDtos();
        exceptionDtos.setMessage("No Products with given ID");
        exceptionDtos.setSolution("Please try again with valid ID");
        ResponseEntity<ExceptionDtos> response = new ResponseEntity<>(exceptionDtos, HttpStatus.NOT_FOUND);

        return response;
    }
}
