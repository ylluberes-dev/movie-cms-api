/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.dao;

import com.ylluberes.moviestore.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    @Query("select u from Movie u where lower(u.title) like lower(concat('%', :titleTarget,'%'))")
    Page<Movie> findAllByTitle (@Param("titleTarget") String title, Pageable pageable);

    @Query("select u from Movie u where lower(u.title) like lower(concat('%', :titleTarget,'%')) and u.available = true")
    Page<Movie> findAllByTitleAndAvailable (@Param("titleTarget") String title, Pageable pageable);

    Page<Movie> findAllByAvailable (boolean available, Pageable pageable);
}
