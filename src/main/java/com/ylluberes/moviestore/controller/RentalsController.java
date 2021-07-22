/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller;

import com.ylluberes.moviestore.controller.request.ActionableRequest;
import com.ylluberes.moviestore.controller.response.OnActionableResponse;
import com.ylluberes.moviestore.domain.type.ActivityDefinition;
import com.ylluberes.moviestore.exceptions.MovieNotAvailableException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rentals")
public class RentalsController {

    @Autowired
    private DeliveryService deliveryService;

    /**
     *
     * @param request rental request
     * @return OnActionableResponse
     */
    @PostMapping()
    public ResponseEntity<OnActionableResponse> rent(@RequestBody final ActionableRequest request) {
        try {
            final OnActionableResponse response =
                      deliveryService.onDeliverFactory(request,
                                                       ActivityDefinition.RENT);
            return new ResponseEntity<>(response,
                                        HttpStatus.CREATED);
        } catch (MovieNotFoundException mf) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (MovieNotAvailableException ma) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
