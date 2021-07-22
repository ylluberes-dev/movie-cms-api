/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service;

import com.ylluberes.moviestore.controller.response.OnTransactionResponse;
import com.ylluberes.moviestore.exceptions.InvalidDateRangeException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;

import java.time.LocalDate;

public interface TransactionService {

    OnTransactionResponse getTransactions (final int movieId,
                                           final LocalDate from,
                                           final LocalDate to) throws MovieNotFoundException,
                                                                      InvalidDateRangeException;
}
