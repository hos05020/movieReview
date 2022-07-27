package org.zerock.moviereview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.moviereview.entity.Member;
import org.zerock.moviereview.entity.Movie;
import org.zerock.moviereview.entity.Review;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews(){
        //200개의 리뷰 등록
        IntStream.rangeClosed(1,200).forEach(i->{

            Long mno = (long)(Math.random()*100)+1; // 영화 번호

            Long mid = ((long)(Math.random()*100)+1);  //리뷰어 번호
            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5)+1)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();

            reviewRepository.save(movieReview);
        });
    }

    @Test
    public void testGetMovieReviews(){
        Movie movie = Movie.builder().mno(100L).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        result.forEach(movieReview->{
            System.out.println(movieReview.getReviewnum());
            System.out.println(movieReview.getGrade());
            System.out.println(movieReview.getText());
            System.out.println(movieReview.getMember().getEmail());
            System.out.println("--------------------------------------");
        });
    }


}