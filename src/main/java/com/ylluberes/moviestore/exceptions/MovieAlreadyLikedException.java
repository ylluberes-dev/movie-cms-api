/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.exceptions;

public class MovieAlreadyLikedException extends RuntimeException{
    public MovieAlreadyLikedException (final String message){
        super(message);
    }
}
