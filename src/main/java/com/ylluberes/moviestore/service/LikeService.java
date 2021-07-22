/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service;

import com.ylluberes.moviestore.controller.request.ActionableRequest;
import com.ylluberes.moviestore.controller.response.OnLikeResponse;
import com.ylluberes.moviestore.exceptions.MovieAlreadyLikedException;
import com.ylluberes.moviestore.exceptions.MovieNotAvailableException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;

public interface LikeService {
    OnLikeResponse like (ActionableRequest request)
                       throws MovieNotFoundException, MovieNotAvailableException, MovieAlreadyLikedException;
}
