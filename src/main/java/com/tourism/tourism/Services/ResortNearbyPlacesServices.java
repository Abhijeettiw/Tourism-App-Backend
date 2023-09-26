package com.tourism.tourism.Services;

import com.tourism.tourism.Payloads.ResortNearbyPlacesDto;

import java.util.List;

public interface ResortNearbyPlacesServices {
    ResortNearbyPlacesDto addPlace(ResortNearbyPlacesDto resortNearbyPlacesDto,Long touristId,Long resortId);
    ResortNearbyPlacesDto updatePlace(ResortNearbyPlacesDto resortNearbyPlacesDto,Long touristId,Long resortId,Long nbPlaceId);
    Void deletePlace(Long touristId,Long resortId,Long nbPlaceId);
    List<ResortNearbyPlacesDto> nbPlaceByResort(Long touristId,Long resortId);
    List<ResortNearbyPlacesDto> nbPlacesByName(String name);
    ResortNearbyPlacesDto nbpById(Long resortId,Long nbpId);

}
