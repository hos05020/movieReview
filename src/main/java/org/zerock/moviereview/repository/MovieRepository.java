package org.zerock.moviereview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.moviereview.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct  r) from Movie m left outer join MovieImage mi on mi.movie = m "+
            " left outer join Review r on r.movie =  m group by m")
    Page<Object[]> getListPage(Pageable pageable); // list 페이지 용 메소드(group by에 의해 mi가 한 개만 반환)

    @Query("select m,mi, avg(coalesce(r.grade,0)), count(distinct  r) from Movie m left outer join MovieImage mi on mi.movie = m left outer join Review r on r.movie = m " +
           " where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(@Param("mno")Long mno); // read페이지용 메소드(mi가 여러개 반환된다->터플이 여러개)
}
