/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service.impl;

import com.ylluberes.moviestore.controller.request.ActionableRequest;
import com.ylluberes.moviestore.controller.response.OnActionableResponse;
import com.ylluberes.moviestore.dao.MovieRepository;
import com.ylluberes.moviestore.dao.RentalsRepository;
import com.ylluberes.moviestore.dao.SaleRepository;
import com.ylluberes.moviestore.domain.Actionable;
import com.ylluberes.moviestore.domain.Movie;
import com.ylluberes.moviestore.domain.Rental;
import com.ylluberes.moviestore.domain.Sale;
import com.ylluberes.moviestore.domain.type.ActivityDefinition;
import com.ylluberes.moviestore.exceptions.MovieNotAvailableException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final SaleRepository saleRepository;

    private final RentalsRepository rentalsRepository;

    private final MovieRepository movieRepository;

    /**
     * @param request  request action made by the customer.
     * @param activity define if the activity was a rent or sale.
     * @return OnActionableResponse
     * @throws MovieNotFoundException     when not found movie.
     * @throws MovieNotAvailableException when movie is not available.
     *                                    <p>
     *                                    Factory method that create a sale or rent based on the dispatched
     *                                    activity
     */
    @Override
    public OnActionableResponse onDeliverFactory(final ActionableRequest request,
                                                 final ActivityDefinition activity) throws MovieNotFoundException,
            MovieNotAvailableException {
        final OnActionableResponse response = new OnActionableResponse();
        Actionable actionable;
        final Optional<Movie> optionalMovie =
                movieRepository.findById(request.getMovieId());

        if (optionalMovie.isPresent()) {
            final Movie movieTarget = optionalMovie.get();
            if (movieTarget.getAvailable() && movieTarget.getStock() > 0) {
                switch (activity) {
                    case RENT:
                        actionable = new Rental();
                        actionable = setActionable(movieTarget, request.getCustomerEmail(), actionable);
                        rentalsRepository.save((Rental) actionable);
                        break;
                    case SALE:
                        actionable = new Sale();
                        actionable = setActionable(movieTarget, request.getCustomerEmail(), actionable);
                        saleRepository.save((Sale) actionable);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown activity");
                }
                movieTarget.updateStock(movieRepository);
            } else {
                throw new MovieNotAvailableException("The movie with id " + request.getMovieId() + " is not available");
            }
            response.setCustomerEmail(actionable.getEmail());
            response.setPrice(activity == ActivityDefinition.RENT ?
                    actionable.getMovie().getRentalPrice()
                    : activity == ActivityDefinition.SALE ?
                    actionable.getMovie().getSalePrice() : 0);

            response.setMovieId(actionable.getMovie().getMovieId());
        } else {
            throw new MovieNotFoundException("There is no movie with id " + request.getMovieId());
        }
        return response;
    }

    /**
     * @param movie      the movie that receive the sale or rent.
     * @param mail       the mail of the customer who buy or rent.
     * @param actionable the object to be set.
     * @return Actionable object
     * Set te properties of incoming sale/rent request to future Actionable instance
     */
    private Actionable setActionable(final Movie movie,
                                     final String mail,
                                     final Actionable actionable) {
        final LocalDate now = LocalDate.of(
                LocalDate.now().getYear(),
                LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth());
        actionable.setMovie(movie);
        actionable.setEmail(mail);
        actionable.setActivityDate(now);
        return actionable;
    }

}
