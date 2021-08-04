/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class OnLikeResponse {

    private int movieId;
    private int likes;
    private Set<String> customers;

}
