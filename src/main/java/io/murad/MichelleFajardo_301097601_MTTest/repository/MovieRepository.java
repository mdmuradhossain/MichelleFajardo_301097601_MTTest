package io.murad.MichelleFajardo_301097601_MTTest.repository;


import io.murad.MichelleFajardo_301097601_MTTest.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}