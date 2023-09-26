package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.ResortFeaturesDto;
import com.tourism.tourism.Services.ResortFeatureServices;
import com.tourism.tourism.Utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/resFeatures")
public class ResortFeaturesController {
    @Autowired
    private ResortFeatureServices resortFeatureServices;
    @PostMapping("/touId/{touristId}/resId/{resortId}/new")
    public ResponseEntity<ResortFeaturesDto> newFeature(@RequestBody ResortFeaturesDto resortFeaturesDto, @PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId)
    {
        ResortFeaturesDto resortFeaturesDto1=this.resortFeatureServices.addFeature(resortFeaturesDto,touristId,resortId);
        return new ResponseEntity<>(resortFeaturesDto1, HttpStatus.CREATED);
    }
    @PutMapping("/touId/{touristId}/resId/{resortId}/nbPlaceId/{nbPlaceId}/update")
    public ResponseEntity<ResortFeaturesDto> updateFeature(@RequestBody ResortFeaturesDto resortFeaturesDto, @PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId,@PathVariable("nbPlaceId") Long nbPlaceId)
    {
        ResortFeaturesDto resortFeaturesDto1=this.resortFeatureServices.updateFeature(resortFeaturesDto,touristId,resortId,nbPlaceId);
        return new ResponseEntity<>(resortFeaturesDto1,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/touId/{touristId}/resId/{resortId}/nbPlaceId/{nbPlaceId}/del")
    public ResponseEntity<ApiResponse> deleteFeature(@PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId,@PathVariable("nbPlaceId") Long nbPlaceId)
    {
        this.resortFeatureServices.deleteFeature(touristId,resortId,nbPlaceId);
        return new ResponseEntity<>(new ApiResponse("Feature deleted Successfully",true,HttpStatus.OK),HttpStatus.OK);
    }
    @GetMapping("/touId/{touristId}/resId/{resortId}/all")
    public ResponseEntity<List<ResortFeaturesDto>> resAllFeature(@PathVariable("touristId") Long touristId, @PathVariable("resortId") Long resortId)
    {
        List<ResortFeaturesDto> all=this.resortFeatureServices.allResortFeatures(touristId,resortId);
        return new ResponseEntity<>(all,HttpStatus.FOUND);
    }
}
