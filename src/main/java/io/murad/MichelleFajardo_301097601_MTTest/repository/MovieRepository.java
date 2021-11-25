package io.murad.MichelleFajardo_301097601_MTTest.repository;

import io.murad.MichelleFajardo_301097601_MTTest.controller.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}