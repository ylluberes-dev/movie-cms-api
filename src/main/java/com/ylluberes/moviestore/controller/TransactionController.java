/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller;

import com.ylluberes.moviestore.controller.response.OnTransactionResponse;
import com.ylluberes.moviestore.exceptions.InvalidDateRangeException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@RestController
@RequestMapping("/transactions")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    /**
     * @param movieId target movie to retrieve transaction
     * @param from    start date
     * @param to      end date
     * @return OnTransactionResponse
     */
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<OnTransactionResponse>
    getTransaction(@PathVariable final int movieId,
                   @RequestParam(name = "from") @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate from,
                   @RequestParam(name = "to") @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate to) {
        try {
            return new
                    ResponseEntity<>(transactionService.getTransactions(movieId, from, to), HttpStatus.OK);
        } catch (MovieNotFoundException mv) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidDateRangeException ie) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
