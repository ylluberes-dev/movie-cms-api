/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.exceptions;

public class InvalidDateRangeException extends RuntimeException {
    public InvalidDateRangeException (final String message) {
        super(message);
    }
}
