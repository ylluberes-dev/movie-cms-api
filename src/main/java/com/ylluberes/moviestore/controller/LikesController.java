/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller;

import com.ylluberes.moviestore.controller.request.ActionableRequest;
import com.ylluberes.moviestore.controller.response.OnLikeResponse;
import com.ylluberes.moviestore.exceptions.MovieAlreadyLikedException;
import com.ylluberes.moviestore.exceptions.MovieNotAvailableException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired
    private LikeService likeService;
    /**
     *
     * @param request The incoming like request
     * @return OnLikeResponse object
     * Create a new like in the repository and associate it with the incoming movie
     */
    @PostMapping
    public ResponseEntity<OnLikeResponse> like(@RequestBody final ActionableRequest request) {
        try {
            final OnLikeResponse response = likeService.like(request);
            return new ResponseEntity<>(response,
                                        HttpStatus.CREATED);
        } catch (MovieNotFoundException mf) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (MovieNotAvailableException ma) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (MovieAlreadyLikedException me){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
