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
 * sale model-definition.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "SALE_SEQ", sequenceName = "sale_seq_no")
public class Sale extends Actionable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALE_SEQ")
    @JsonIgnore
    private int saleId;
}
