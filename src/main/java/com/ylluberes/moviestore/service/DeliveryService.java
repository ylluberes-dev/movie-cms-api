/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service;

import com.ylluberes.moviestore.controller.request.ActionableRequest;
import com.ylluberes.moviestore.controller.response.OnActionableResponse;
import com.ylluberes.moviestore.domain.type.ActivityDefinition;
import com.ylluberes.moviestore.exceptions.MovieNotAvailableException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;

public interface DeliveryService {
    OnActionableResponse onDeliverFactory (ActionableRequest request,
                                           ActivityDefinition activity) throws MovieNotFoundException,
            MovieNotAvailableException;

}
