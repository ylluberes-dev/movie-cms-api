/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller;

import com.ylluberes.moviestore.controller.request.AddOrUpdateMovieRequest;
import com.ylluberes.moviestore.controller.request.FindMovieRequest;
import com.ylluberes.moviestore.controller.request.PatchRequest;
import com.ylluberes.moviestore.controller.response.OnMoviesResponse;
import com.ylluberes.moviestore.domain.Movie;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * @param request movie body payload
     */
    @PostMapping()
    public ResponseEntity<Movie> register(@RequestBody @Valid final AddOrUpdateMovieRequest request) {
        return new ResponseEntity<>(movieService.save(request),
                                    HttpStatus.CREATED);
    }

    /**
     * @param movieId unique identifier of the movie to update
     * @param request movie payload to update
     * @return Updated Movie object
     */
    @PutMapping("/{movieId}")
    public ResponseEntity<Movie> update(@PathVariable final int movieId,
                                        @RequestBody @Valid final AddOrUpdateMovieRequest request) {
        try {
            return new ResponseEntity<>(movieService.update(movieId,
                                                            request),
                                                            HttpStatus.OK);
        } catch (MovieNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param movieId unique identifier of the movie to update
     * @param request movie payload with movie changes
     * @return Updated Movie object
     */
    @PatchMapping("/{movieId}")
    public ResponseEntity<Movie> patch(@PathVariable final int movieId,
                                       @RequestBody @Valid  final PatchRequest request) {
        System.out.println("");
        try {
            return new ResponseEntity<>(movieService.update(movieId,
                                                            request),
                                                            HttpStatus.OK);
        }catch (MovieNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param movieId unique identifier of the movie to delete
     */
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> remove(@PathVariable final int movieId) {
        try {
            movieService.delete(movieId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (MovieNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param size number of elements that must be showed in one page
     * @param page current page
     * @param unavailable by default, only available movies are showed, with unavailable = true then unavailable movies are showed too
     * @param title no required parameter to filter movies by title
     * @param sort order to sort the movies content page ex: title,desc
     * @return OnMovieResponse Object representing the output of paginated movies
     */
    @GetMapping()
    public ResponseEntity<OnMoviesResponse> getMovies(@RequestParam(required = false, defaultValue = "12") final int size,
                                                      @RequestParam(required = false, defaultValue = "0") final int page,
                                                      @RequestParam(required = false,defaultValue = "false") final boolean unavailable,
                                                      @RequestParam(required = false) final String title,
                                                      @RequestParam(required = false, defaultValue = "title,asc") final String sort) {


        final Page<Movie> moviePage =
               movieService.getMovies(new FindMovieRequest(size, page, unavailable, title, sort));

        final OnMoviesResponse response = new OnMoviesResponse();
        response.setContent(moviePage.getContent());
        response.setNumber(moviePage.getNumber());
        response.setSize(moviePage.getSize());
        response.setTotalElements((int)moviePage.getTotalElements());
        response.setTotalPages(moviePage.getTotalPages());
        response.setNumberOfElements(moviePage.getNumberOfElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
