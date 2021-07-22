/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service.impl;

import com.ylluberes.moviestore.controller.request.ActionableRequest;
import com.ylluberes.moviestore.controller.response.OnLikeResponse;
import com.ylluberes.moviestore.dao.LikeRepository;
import com.ylluberes.moviestore.dao.MovieRepository;
import com.ylluberes.moviestore.domain.Likes;
import com.ylluberes.moviestore.domain.Movie;
import com.ylluberes.moviestore.exceptions.MovieAlreadyLikedException;
import com.ylluberes.moviestore.exceptions.MovieNotAvailableException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private LikeRepository likeRepository;

    /**
     *
     * @param request Incoming like request
     * @return OnLikeResponse
     * @throws MovieNotFoundException when movie not found
     * @throws MovieNotAvailableException when the movie is not available
     * @throws MovieAlreadyLikedException when the movie is already liked by the same customer
     */
    @Override
    public OnLikeResponse like(final ActionableRequest request)
            throws MovieNotFoundException, MovieNotAvailableException, MovieAlreadyLikedException{
        final Optional<Movie> optionalMovie =
                movieRepository.findById(request.getMovieId());

        if (optionalMovie.isPresent()) {
            final Movie movieToLike = optionalMovie.get();
            if (movieToLike.getAvailable()) {
                if(likeRepository.existsLikesByEmailAndMovie(request.getCustomerEmail(),movieToLike))
                    throw new MovieAlreadyLikedException("Movie is already liked by this customer");
                final Likes givenLike = new Likes();
                givenLike.setEmail(request.getCustomerEmail());
                givenLike.setMovie(movieToLike);
                likeRepository.save(givenLike);

                final OnLikeResponse response = new OnLikeResponse();
                response.setCustomers(extractWhoLikes(movieToLike));
                response.setLikes(movieToLike.getLikes().size());
                response.setMovieId(movieToLike.getMovieId());
                return response;
            } else {
                throw new MovieNotAvailableException("The movie with id " + request.getMovieId() + " is not available");
            }
        } else {
            throw new MovieNotFoundException("There is no movie with id " + request.getMovieId());
        }
    }


    /**
     * @param movie liked.
     * @return all distinct customer emails who like this movie.
     */
    private List<String> extractWhoLikes(final Movie movie) {
        List<String> likesList = new ArrayList<>();
        if (movie.getLikes().size() > 0) {
            movie.getLikes().forEach(like -> {
                likesList.add(like.getEmail());
            });
        }
        return likesList;
    }
}
