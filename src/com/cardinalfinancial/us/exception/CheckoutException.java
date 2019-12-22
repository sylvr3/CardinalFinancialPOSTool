package com.cardinalfinancial.us.exception;

/**
 * Custom exception for this project/application
 **/
public class CheckoutException extends RuntimeException {

    /**
     * Default constructor
     */
    public CheckoutException() {
        super("CheckoutException thrown");
    }

    /**
     * Constructor
     *
     * @param message detailed message that explains why the exception occurred
     */
    public CheckoutException(String message) {
        super(message);
    }

    /**
     * Create a new InvalidDataTypeException with the given message and Exception
     *
     * @param message the exception message
     * @param e       the Exception e
     */
    public CheckoutException(String message, Exception e) {
        super(message, e);
    }
}
