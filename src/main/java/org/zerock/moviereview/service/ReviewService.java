package org.zerock.moviereview.service;

import org.zerock.moviereview.dto.ReviewDTO;
import org.zerock.moviereview.entity.Member;
import org.zerock.moviereview.entity.Movie;
import org.zerock.moviereview.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListofMovie(Long mno);

    Long register(ReviewDTO movieReviewDTO);

    void modify(ReviewDTO movieReviewDTO);

    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO){
        Review movieReview = Review.builder()
                .reviewnum(movieReviewDTO.getReviewnum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();
        return movieReview;
    }

    default ReviewDTO entityToDTO(Review movieReview){
        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .reviewnum(movieReview.getReviewnum())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid())
                .nickname(movieReview.getMember().getNickname())
                .email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate())
                .build();
        return movieReviewDTO;
    }
}