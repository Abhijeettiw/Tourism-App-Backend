package com.tourism.tourism.Services;

import com.tourism.tourism.Payloads.TouristDto;

import java.util.List;

public interface TouristPlaceServices {
    TouristDto addPlace(TouristDto touristDto);
    TouristDto updatePlace(TouristDto touristDto,Long touristId);
    Void deletePlace(Long touristId);
    TouristDto placeById(Long touristId);
    List<TouristDto> allPlace(Integer pageNo,Integer pageSize);
    List<TouristDto> findByName(String name);

}
