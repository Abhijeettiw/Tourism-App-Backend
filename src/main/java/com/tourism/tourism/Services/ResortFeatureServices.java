package com.tourism.tourism.Services;

import com.tourism.tourism.Payloads.ResortDto;
import com.tourism.tourism.Payloads.ResortFeaturesDto;
import java.util.List;

public interface ResortFeatureServices {

    ResortFeaturesDto addFeature(ResortFeaturesDto resortFeaturesDto, Long touristId, Long resortId);
    ResortFeaturesDto updateFeature(ResortFeaturesDto resortFeaturesDto,Long touristId,Long resortId,Long nbPlaceId);
    Void deleteFeature(Long touristId,Long resortId,Long nbPlaceId);
    List<ResortFeaturesDto> allResortFeatures(Long touristId, Long resortId);
}
