package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.ResortReviews;
import com.tourism.tourism.Entities.Resorts;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.ResortReviewDto;
import com.tourism.tourism.Repositories.ResortRepo;
import com.tourism.tourism.Repositories.ResortReviewsRepo;
import com.tourism.tourism.Repositories.TouristRepo;
import com.tourism.tourism.Services.ResortReviewsServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResortReviewServicesImpl implements ResortReviewsServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TouristRepo touristRepo;
    @Autowired
    private ResortRepo resortRepo;
    @Autowired
    private ResortReviewsRepo resortReviewsRepo;
    @Override
    public ResortReviewDto newReview(ResortReviewDto resortReviewDto,Long touristId, Long resortId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        ResortReviews resortReviews=this.modelMapper.map(resortReviewDto,ResortReviews.class);
        resortReviews.setResorts(resorts);
        resortReviews.setReviews(resortReviewDto.getReviews());
        this.resortReviewsRepo.save(resortReviews);
        return this.modelMapper.map(resortReviews,ResortReviewDto.class);
    }

    @Override
    public ResortReviewDto updateReview(ResortReviewDto resortReviewDto, Long touristId, Long resortId, Long resortReviewId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        ResortReviews resortReviews=this.resortReviewsRepo.findById(resortReviewId).orElseThrow(() -> new ResourceNotFoundException("name", resortReviewId));
        resortReviews.setReviews(resortReviewDto.getReviews());
        this.resortReviewsRepo.save(resortReviews);
        return this.modelMapper.map(resortReviews,ResortReviewDto.class);
    }

    @Override
    public Void deleteReview(Long touristId, Long resortId, Long resortReviewId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        ResortReviews resortReviews=this.resortReviewsRepo.findById(resortReviewId).orElseThrow(() -> new ResourceNotFoundException("name", resortReviewId));
        this.resortReviewsRepo.delete(resortReviews);
        return null;
    }

    @Override
    public ResortReviewDto resortReviewGetById(Long touristId, Long resortId, Long resortReviewId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        ResortReviews resortReviews=this.resortReviewsRepo.findById(resortReviewId).orElseThrow(() -> new ResourceNotFoundException("name", resortReviewId));
        return this.modelMapper.map(resortReviews,ResortReviewDto.class);
    }

    @Override
    public List<ResortReviewDto> allReviewsByResort(Long touristId, Long resortId) {
        this.touristRepo.findById(touristId).orElseThrow(() -> new ResourceNotFoundException("name", touristId));
        Resorts resorts=this.resortRepo.findById(resortId).orElseThrow(() -> new ResourceNotFoundException("name", resortId));
        List<ResortReviews> resortReviewsList=this.resortReviewsRepo.findByResorts(resorts);
        return resortReviewsList.stream().map((rRL)->this.modelMapper.map(rRL,ResortReviewDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ResortReviewDto> allResortReviews() {
        List<ResortReviews> allResortReviewsList=this.resortReviewsRepo.findAll();
        return allResortReviewsList.stream().map((rRL)->this.modelMapper.map(rRL,ResortReviewDto.class)).collect(Collectors.toList());
    }
}
