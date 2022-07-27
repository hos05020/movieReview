package org.zerock.moviereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.moviereview.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage,Long> {
}
