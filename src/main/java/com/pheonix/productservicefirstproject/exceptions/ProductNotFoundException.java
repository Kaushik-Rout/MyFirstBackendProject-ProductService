package com.pheonix.productservicefirstproject.exceptions;

// Its my personal exception handler in the place of NullPointerException - cause same error message for all nullPointerException is not a good ides.
// so we should create custom exception
// Note: GlobalExceptionHandler doesn't have any func() to handle ProductNotFoundException - so we need to create one !
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message); // implements parent class's constructor (depending on if parent got parameterized const. or not).
    }
}
