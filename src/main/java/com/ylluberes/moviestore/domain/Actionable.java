/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;


/**
 * Represents a base abstract class for sale and rent model-definition classes.
 * An actionable is common event for rent and sale
 */
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class Actionable {

    @JsonIgnore
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    @JsonIgnore
    private Movie movie;
    private LocalDate activityDate;


}
