/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ActionableRequest {

    private int movieId;
    private String customerEmail;
}
