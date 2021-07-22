/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.dao;

import com.ylluberes.moviestore.domain.Likes;
import com.ylluberes.moviestore.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LikeRepository extends JpaRepository<Likes, Integer> {
    boolean existsLikesByEmailAndMovie(String email, Movie movie);
}
