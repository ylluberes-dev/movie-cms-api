/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service.impl;

import com.ylluberes.moviestore.controller.response.OnTransactionResponse;
import com.ylluberes.moviestore.dao.MovieRepository;
import com.ylluberes.moviestore.domain.Movie;
import com.ylluberes.moviestore.domain.Rental;
import com.ylluberes.moviestore.domain.Sale;
import com.ylluberes.moviestore.exceptions.InvalidDateRangeException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.TransactionService;
import com.ylluberes.moviestore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * @param movieId id of the movie target.
     * @param from    start date
     * @param to      end date
     * @return OnTransactionResponse
     * @throws MovieNotFoundException when movie not found
     * @throws InvalidDateRangeException when from > to
     * Retrieve all transactions that has been made (sale,rent) between date range
     */
    @Override
    public OnTransactionResponse getTransactions(final int movieId,
                                                 final LocalDate from,
                                                 final LocalDate to)  throws MovieNotFoundException,
                                                                             InvalidDateRangeException {
        if(from.isAfter(to)) throw new InvalidDateRangeException("To parameter should be greater than From parameter");
        final Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isPresent()) {
            final Movie movie = optionalMovie.get();
            final OnTransactionResponse response = new OnTransactionResponse();
            final Set<String> customerMails = new HashSet<>();
            final Set<LocalDate> rentalsDate = new HashSet<>();
            final Set<LocalDate> salesDate = new HashSet<>();

            for (Rental rental : movie.getRentals()) {
                if (Util.isInRange(rental.getActivityDate(), from, to))
                    rentalsDate.add(rental.getActivityDate());
                customerMails.add(rental.getEmail());
            }
            for (Sale sale : movie.getSales()) {
                if (Util.isInRange(sale.getActivityDate(), from, to))
                    salesDate.add(sale.getActivityDate());
                customerMails.add(sale.getEmail());
            }
            response.setRentals(rentalsDate);
            response.setSales(salesDate);
            response.setTotalRevenue(movie.getRevenue());
            response.setCustomers(customerMails);
            response.setMovieId(movie.getMovieId());
            return response;
        } else {
            throw new MovieNotFoundException("There is no movie with id " + movieId);
        }
    }

}
