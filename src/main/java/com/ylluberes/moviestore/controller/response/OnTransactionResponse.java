/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class OnTransactionResponse {

    private int movieId;
    private Set<LocalDate> rentals;
    private Set<LocalDate> sales;
    private double totalRevenue;
    private Set<String> customers;


}
