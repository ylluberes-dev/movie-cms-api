/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.exceptions;

public class MovieNotAvailableException extends RuntimeException {
    public MovieNotAvailableException(final String message) {
        super(message);
    }
}
