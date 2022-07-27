package org.zerock.moviereview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.moviereview.dto.ReviewDTO;
import org.zerock.moviereview.entity.Movie;
import org.zerock.moviereview.entity.Review;
import org.zerock.moviereview.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListofMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<Review> result = reviewRepository.findByMovie(movie);

        return result.stream().map(movieReview->entityToDTO(movieReview)).collect(Collectors.toList());

    }

    @Override
    public Long register(ReviewDTO movieReviewDTO) {
        Review movieReview = dtoToEntity(movieReviewDTO);
        reviewRepository.save(movieReview);
        return movieReview.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO movieReviewDTO) {
        Optional<Review> result = reviewRepository.findById(movieReviewDTO.getReviewnum());
        if(result.isPresent()){
            Review movieReview = result.get();
            movieReview.changeGrade(movieReviewDTO.getGrade());
            movieReview.changeText(movieReviewDTO.getText());

            reviewRepository.save(movieReview);

        }
    }

    @Override
    public void remove(Long reviewnum) {
          reviewRepository.deleteById(reviewnum);
    }
}
