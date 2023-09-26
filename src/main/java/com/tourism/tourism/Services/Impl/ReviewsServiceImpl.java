package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.Reviews;
import com.tourism.tourism.Entities.TouristPlaces;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.ReviewsDto;
import com.tourism.tourism.Repositories.ReviewsRepo;
import com.tourism.tourism.Repositories.TouristRepo;
import com.tourism.tourism.Services.ReviewsServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ReviewsServiceImpl implements ReviewsServices {
    @Autowired
    private TouristRepo touristRepo;
    @Autowired
    private ReviewsRepo reviewsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ReviewsDto newReview(ReviewsDto reviewsDto,Long touristId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Reviews reviews=this.modelMapper.map(reviewsDto,Reviews.class);
        reviews.setReviews(reviewsDto.getReviews());
        reviews.setTouristPlaces(touristPlaces);
        this.reviewsRepo.save(reviews);
        return this.modelMapper.map(reviews,ReviewsDto.class);
    }

    @Override
    public ReviewsDto updateReview(ReviewsDto reviewsDto,Long touristId, Long reviewId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(()-> new ResourceNotFoundException("name",touristId));
        Reviews reviews=this.reviewsRepo.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("name", reviewId));
        reviews.setReviews(reviewsDto.getReviews());
        this.reviewsRepo.save(reviews);
        return this.modelMapper.map(reviews,ReviewsDto.class);
    }

    @Override
    public Void deleteReview(Long touristId,Long reviewId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(()->new ResourceNotFoundException("name",touristId));
        Reviews reviews=this.reviewsRepo.findById(reviewId).orElseThrow(()->new ResourceNotFoundException("name",reviewId));
        this.reviewsRepo.delete(reviews);
        return null;
    }

    @Override
    public ReviewsDto reviewGetById(Long touristId,Long reviewId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Reviews reviews=this.reviewsRepo.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("name", reviewId));
        return this.modelMapper.map(reviews,ReviewsDto.class);

    }

    @Override
    public List<ReviewsDto> allReviewsBySite(Long touristId) {
        TouristPlaces touristPlaces=this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        List<Reviews> reviewsList=this.reviewsRepo.findByTouristPlaces(touristPlaces);
        List<ReviewsDto> reviewsDtoList=reviewsList.stream().map((re)->this.modelMapper.map(re,ReviewsDto.class)).collect(Collectors.toList());
        return reviewsDtoList;
    }

    @Override
    public List<ReviewsDto> allReviews() {
        List<Reviews>  reviewsList=this.reviewsRepo.findAll();
        List<ReviewsDto> reviewsDtoList=reviewsList.stream().map((re)->this.modelMapper.map(re,ReviewsDto.class)).collect(Collectors.toList());
        return reviewsDtoList;
    }
}
