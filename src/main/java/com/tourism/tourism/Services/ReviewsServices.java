package com.tourism.tourism.Services;

import com.tourism.tourism.Entities.Reviews;
import com.tourism.tourism.Entities.TouristPlaces;
import com.tourism.tourism.Payloads.ReviewsDto;

import java.util.List;

public interface ReviewsServices {
    ReviewsDto newReview(ReviewsDto reviewsDto,Long touristId);
    ReviewsDto updateReview(ReviewsDto reviewsDto,Long touristId, Long reviewId);
    Void deleteReview(Long touristId,Long reviewId);
    ReviewsDto reviewGetById(Long touristId,Long reviewId);
    List<ReviewsDto> allReviewsBySite(Long touristId);
    List<ReviewsDto> allReviews();
}
