package com.tourism.tourism.Services;

import com.tourism.tourism.Payloads.ResortReviewDto;

import java.util.List;

public interface ResortReviewsServices {
    ResortReviewDto newReview(ResortReviewDto resortReviewDto,Long touristId, Long resortId);
    ResortReviewDto updateReview(ResortReviewDto resortReviewDto,Long touristId, Long resortId,Long resortReviewId);
    Void deleteReview(Long touristId,Long resortId,Long resortReviewId);
    ResortReviewDto resortReviewGetById(Long touristId,Long resortId,Long reviewId);
    List<ResortReviewDto> allReviewsByResort(Long touristId,Long resortId);
    List<ResortReviewDto> allResortReviews();


}
