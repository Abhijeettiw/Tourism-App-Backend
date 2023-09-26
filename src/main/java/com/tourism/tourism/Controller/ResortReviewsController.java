package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.ResortReviewDto;
import com.tourism.tourism.Services.ResortReviewsServices;
import com.tourism.tourism.Utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/resRev")
public class ResortReviewsController {
    @Autowired
    private ResortReviewsServices resortReviewsServices;
    @PostMapping("/touId/{touristId}/resId/{resortId}/new")
    public ResponseEntity<ResortReviewDto> addResortReview(@RequestBody ResortReviewDto resortReviewDto, @PathVariable("touristId") Long touristId,@PathVariable("resortId") Long resortId)
    {
        ResortReviewDto resortReviewDto1=this.resortReviewsServices.newReview(resortReviewDto,touristId,resortId);
        return new ResponseEntity<>(resortReviewDto1, HttpStatus.ACCEPTED);
    }
    @PutMapping("/touId/{touristId}/resId/{resortId}/resRevId/{resortReviewId}/update")
    public ResponseEntity<ResortReviewDto> updateResortReviews(@RequestBody ResortReviewDto resortReviewDto, @PathVariable("touristId") Long touristId,@PathVariable("resortId") Long resortId,@PathVariable("resortReviewId") Long resortReviewId)
    {
        ResortReviewDto resortReviewDto1=this.resortReviewsServices.updateReview(resortReviewDto,touristId,resortId,resortReviewId);
        return new ResponseEntity<>(resortReviewDto1,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/touId/{touristId}/resId/{resortId}/resRevId/{resortReviewId}/del")
    public ResponseEntity<ApiResponse> deleteResortReview( @PathVariable("touristId") Long touristId,@PathVariable("resortId") Long resortId,@PathVariable("resortReviewId") Long resortReviewId)
    {
        this.resortReviewsServices.deleteReview(touristId,resortId,resortReviewId);
        return new ResponseEntity<>(new ApiResponse("Review Deleted Successfully",true,HttpStatus.OK),HttpStatus.OK);
    }
    @GetMapping("/touId/{touristId}/resId/{resortId}/resRevId/{resortReviewId}")
    public ResponseEntity<ResortReviewDto> resRevById(@PathVariable("touristId") Long touristId,@PathVariable("resortId") Long resortId,@PathVariable("resortReviewId") Long resortReviewId)
    {
        ResortReviewDto resortReviewDto=this.resortReviewsServices.resortReviewGetById(touristId,resortId,resortReviewId);
        return new ResponseEntity<>(resortReviewDto,HttpStatus.FOUND);
    }
    @GetMapping("/touId/{touristId}/resId/{resortId}/revs")
    public ResponseEntity<List<ResortReviewDto>> reviewByResort(@PathVariable("touristId") Long touristId,@PathVariable("resortId") Long resortId)
    {
        List<ResortReviewDto> resortReviewDto=this.resortReviewsServices.allReviewsByResort(touristId,resortId);
        return new ResponseEntity<>(resortReviewDto,HttpStatus.FOUND);
    }
    @GetMapping("/allResRev")
    public ResponseEntity<List<ResortReviewDto>> allResRev()
    {
        List<ResortReviewDto> resortsReviewsList=this.resortReviewsServices.allResortReviews();
        return new ResponseEntity<>(resortsReviewsList,HttpStatus.FOUND);
    }
}
