/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Like model-definition.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "LIKE_SEQ", sequenceName = "like_seq_no")
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"email", "movieId"})
)
public class Likes  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_SEQ")
    private int likeId;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    private Movie movie;

}
