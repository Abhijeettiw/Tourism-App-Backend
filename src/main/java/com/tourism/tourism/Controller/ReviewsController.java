package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.ReviewsDto;
import com.tourism.tourism.Services.ReviewsServices;
import com.tourism.tourism.Utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/siteReview")
public class ReviewsController {
    @Autowired
    private ReviewsServices reviewsServices;
    @PostMapping("/site/touId/{touristId}/new")
    public ResponseEntity<ReviewsDto> addReview(@RequestBody ReviewsDto reviewsDto, @PathVariable("touristId") Long touristId)
    {
        ReviewsDto newReview=this.reviewsServices.newReview(reviewsDto,touristId);
        return new ResponseEntity<>(newReview, HttpStatus.ACCEPTED);
    }
    @PutMapping("/site/{touristId}/revId/{reviewId}/update")
    public ResponseEntity<ReviewsDto> updateReview(@RequestBody ReviewsDto reviewsDto,@PathVariable("touristId") Long touristID, @PathVariable("reviewId") Long reviewId)
    {
        ReviewsDto updateReview=this.reviewsServices.updateReview(reviewsDto,touristID,reviewId);
        return new ResponseEntity<>(updateReview,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/site/touId/{touristId}/revId/{reviewId}/del")
    public  ResponseEntity<ApiResponse> deleteReview(@PathVariable("touristId") Long touristId,@PathVariable("reviewId") Long reviewId)
    {
       this.reviewsServices.deleteReview(touristId,reviewId);
       return new ResponseEntity<>(new ApiResponse("Review Deleted Successfully",true,HttpStatus.OK),HttpStatus.OK);
    }
    @GetMapping("/site/{touristId}/revId/{reviewId}")
    public ResponseEntity<ReviewsDto> revGetById(@PathVariable("touristId") Long touristId,@PathVariable("reviewId") Long reviewId)
    {
        ReviewsDto reviewsDto=this.reviewsServices.reviewGetById(touristId,reviewId);
        return new ResponseEntity<>(reviewsDto,HttpStatus.FOUND);
    }
    @GetMapping("/site/touId/{touristId}/revs")
    public ResponseEntity<List<ReviewsDto>> reviewsBySite(@PathVariable("touristId") Long touristId)
    {
        List<ReviewsDto> reviewBySite=this.reviewsServices.allReviewsBySite(touristId);
        return new ResponseEntity<>(reviewBySite,HttpStatus.FOUND);
    }
    @GetMapping("/site/allReviews")
    public ResponseEntity<List<ReviewsDto>> allReview()
    {
        List<ReviewsDto> allReviews=this.reviewsServices.allReviews();
        return new ResponseEntity<>(allReviews,HttpStatus.FOUND);
    }
}
