package com.tourism.tourism.Services;

import com.tourism.tourism.Entities.TouristPlaces;
import com.tourism.tourism.Payloads.ResortDto;

import java.util.List;

public interface ResortServices {
    ResortDto addResort(ResortDto resortDto,Long touristId);
    ResortDto updateResort(ResortDto resortDto,Long resortId);
    Void deleteResort(Long touristId,Long resortId);
    ResortDto resortById(Long resortId);
    List<ResortDto> allResort(Integer pageNo,Integer pageSize);
    List<ResortDto> resortByPlace(Long touristId);
    List<ResortDto> featuredResorts(Long touristId);
    List<ResortDto> resortByName(String name);
    ResortDto feature(ResortDto resortDto,Long touristId,Long resortId);

}
