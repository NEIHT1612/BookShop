package com.springboot_react.service;

import com.springboot_react.dao.BookRepository;
import com.springboot_react.dao.ReviewRepository;
import com.springboot_react.entity.Review;
import com.springboot_react.requestmodels.RequestReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, RequestReview requestReview) throws Exception{
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, requestReview.getBookId());
        if(validateReview != null){
            throw new Exception("Review already created");
        }

        Review review = new Review();
        review.setBookId(requestReview.getBookId());
        review.setRating(requestReview.getRating());
        review.setUserEmail(userEmail);
        if(requestReview.getReviewDescription().isPresent()){
            review.setReviewDescription(requestReview.getReviewDescription().map(Object::toString).orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }

    public boolean userReviewListed(String userEmail, Long bookId){
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        if(validateReview != null){
            return true;
        }
        else{
            return false;
        }
    }
}
