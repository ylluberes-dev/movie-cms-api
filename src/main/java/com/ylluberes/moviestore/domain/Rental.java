/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


/**
 * Rent model-definition.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "MOVIE_SEQ", sequenceName = "movie_seq_no")
public class Rental extends Actionable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_SEQ")
    @JsonIgnore
    private int rentalId;

}
